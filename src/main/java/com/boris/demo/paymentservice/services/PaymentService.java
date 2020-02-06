package com.boris.demo.paymentservice.services;

import com.boris.demo.paymentservice.model.PageDTO;
import com.boris.demo.paymentservice.model.Payment;
import com.boris.demo.paymentservice.model.PaymentRequest;

import java.util.UUID;

public interface PaymentService {
    UUID initializePayment(PaymentRequest paymentRequest);

    PageDTO<Payment> getPayments(int page, int size);

    Payment getPaymentById(UUID paymentId);
}
