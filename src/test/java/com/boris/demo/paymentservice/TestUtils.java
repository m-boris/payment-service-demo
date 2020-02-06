package com.boris.demo.paymentservice;

import com.boris.demo.paymentservice.enums.PaymentMethodNameEnum;
import com.boris.demo.paymentservice.model.PaymentMethod;
import com.boris.demo.paymentservice.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TestUtils {

    private static final String NAMES_RESOURCE_HOLDER="/names.txt";

    static List<String> names = initNames();

    private static List<String> initNames()  {
        String text = new Scanner(CreateUsersIntegrationTest.class.getResourceAsStream( NAMES_RESOURCE_HOLDER ), "UTF-8").next();
        String[] names = text.split( "," );
        return Arrays.asList( names );
    }

    public static User generateUser() {
        String fullUserName = generateName();
        String email = fullUserName.replace(" ", ".");
        email+="@gmail.com";
        List<PaymentMethod> paymentMethods = generatePaymentMethods();
        return User.builder()
                .fullName(String.format( "%s (tz:%s)", fullUserName, RandomStringUtils.randomNumeric( 9 ) ) )
                .email( email )
                .paymentMethods(paymentMethods )
                .build();
    }

    public static List<PaymentMethod> generatePaymentMethods() {
        return Arrays.stream( PaymentMethodNameEnum.values() ).
                map( pm -> PaymentMethod.builder()
                        .paymentMethodName( pm )
                        .paymentData(RandomStringUtils.randomNumeric( 16 ))
                        .build()
                ).
                collect( Collectors.toList() );
    }

    public static String generateName() {
        String firstName = names.get( RandomUtils.nextInt( 0, names.size() ) );
        String lastName = names.get( RandomUtils.nextInt( 0, names.size() ) );
        return String.format( "%s %s", firstName, lastName );
    }
}
