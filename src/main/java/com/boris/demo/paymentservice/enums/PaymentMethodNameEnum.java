package com.boris.demo.paymentservice.enums;

public enum PaymentMethodNameEnum {
    VISA("Visa"),AMEX("AMEX"),MASTER_CARD("Master card"),BANK_ACCOUNT("Bank Account");

    private final String displayName;

    PaymentMethodNameEnum(String displayName) {
        this.displayName = displayName;
    }
}
