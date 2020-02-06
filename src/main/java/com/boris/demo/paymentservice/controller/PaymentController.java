package com.boris.demo.paymentservice.controller;

import com.boris.demo.paymentservice.model.PageDTO;
import com.boris.demo.paymentservice.model.Payment;
import com.boris.demo.paymentservice.model.PaymentRequest;
import com.boris.demo.paymentservice.services.MessageService;
import com.boris.demo.paymentservice.services.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Slf4j
@RestController
public class PaymentController {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MessageService messageService;

    @SneakyThrows
    @PostMapping( "/payment/init" )
    public UUID doPay(@RequestBody PaymentRequest paymentRequest){
        log.info( "Start payment, requested object: {}", objectMapper.writeValueAsString( paymentRequest ) );
        UUID paymentId = paymentService.initializePayment(paymentRequest);
        paymentRequest.setId( paymentId );
        messageService.sendPaymentRequest( paymentRequest );
        return paymentId;
    }

    @GetMapping( "/payments" )
    public PageDTO<Payment> getPayments(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size){
        return paymentService.getPayments( page, size );
    }

    @GetMapping( "/payment/{paymentId}" )
    public Payment getPayment( @PathVariable UUID paymentId ){
        return paymentService.getPaymentById( paymentId );
    }
}
