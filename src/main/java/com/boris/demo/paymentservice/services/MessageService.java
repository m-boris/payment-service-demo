package com.boris.demo.paymentservice.services;

import com.boris.demo.paymentservice.model.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageService {
    void sendPaymentRequest(PaymentRequest paymentRequest) throws JsonProcessingException;
}
