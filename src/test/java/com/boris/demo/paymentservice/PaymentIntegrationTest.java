package com.boris.demo.paymentservice;

import com.boris.demo.paymentservice.model.PaymentRequest;
import com.boris.demo.paymentservice.model.PaymentMethod;
import com.boris.demo.paymentservice.model.User;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public class PaymentIntegrationTest implements IIntegrationTest {


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
        restTemplate.postForObject(getFullUrl("/payment/init"), paymentRequest, UUID.class);
    }

    private User createUser() {
        User user = TestUtils.generateUser();
        RestTemplate restTemplate = new RestTemplate();
        user = restTemplate.postForObject(getFullUrl("/user"), user, User.class);
        return user;
    }
}
