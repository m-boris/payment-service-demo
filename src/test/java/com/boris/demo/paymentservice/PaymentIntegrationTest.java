package com.boris.demo.paymentservice;

import com.boris.demo.paymentservice.enums.PaymentStatusEnum;
import com.boris.demo.paymentservice.model.Payment;
import com.boris.demo.paymentservice.model.PaymentRequest;
import com.boris.demo.paymentservice.model.PaymentMethod;
import com.boris.demo.paymentservice.model.User;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.boris.demo.paymentservice.enums.PaymentStatusEnum.REJECTED;
import static org.hamcrest.MatcherAssert.assertThat;

public class PaymentIntegrationTest implements IIntegrationTest {


    public static final PaymentStatusEnum[] VALID_STATUSES = {PaymentStatusEnum.ACCEPTED, PaymentStatusEnum.REJECTED};

    @SneakyThrows
    @Test
    public void paymentTest(){
        User payingUser = createUser();
        PaymentMethod paymentMethod = payingUser.getPaymentMethods().get(RandomUtils.nextInt( 0, payingUser.getPaymentMethods().size() ) );
        User payeeUser = createUser();

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .userId( payingUser.getId() )
                .payeeId( payeeUser.getId() )
                .paymentMethod( paymentMethod.getId() )
                .amount( new BigDecimal( "70.5" ))
                .currency(Currency.getInstance( "USD" ) )
                .build();
        RestTemplate restTemplate = new RestTemplate();
        UUID paymentId = restTemplate.postForObject(getFullUrl("/payment/init"), paymentRequest, UUID.class);
        // wait for risk engine
        Thread.sleep( 1000 );
        Payment payment = restTemplate.getForObject(getFullUrl("/payment/"+paymentId ), Payment.class);
        PaymentStatusEnum paymentStatusEnum = payment.getPaymentStatus();
         assertThat( paymentStatusEnum, Matchers.in( VALID_STATUSES ) );
    }

    private User createUser() {
        User user = TestUtils.generateUser();
        RestTemplate restTemplate = new RestTemplate();
        user = restTemplate.postForObject(getFullUrl("/user"), user, User.class);
        return user;
    }
}
