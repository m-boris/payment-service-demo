package com.boris.demo.paymentservice.services.impl;

import com.boris.demo.paymentservice.model.PaymentRequest;
import com.boris.demo.paymentservice.services.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value( "${PAYMENT_TOPIC_NAME:payment_topic}" )
    private String paymentTopic;

    @Override
    public void sendPaymentRequest(PaymentRequest paymentRequest) throws JsonProcessingException {
        kafkaTemplate.send( paymentTopic, objectMapper.writeValueAsString( paymentRequest ) );
    }
}
