package com.boris.demo.paymentservice;

import com.boris.demo.paymentservice.enums.PaymentMethodNameEnum;
import com.boris.demo.paymentservice.model.PaymentMethod;
import com.boris.demo.paymentservice.model.User;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class CreateUsersIntegrationTest implements IIntegrationTest {


    // please startup the server before executing
    @Test
    public void createUser(){
        User user = TestUtils.generateUser();
        RestTemplate restTemplate = new RestTemplate();
        user = restTemplate.postForObject( getFullUrl("/user"), user, User.class );
        assertThat( user.getId(), Matchers.notNullValue() );
    }


}
