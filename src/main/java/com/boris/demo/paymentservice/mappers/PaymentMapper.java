package com.boris.demo.paymentservice.mappers;

import com.boris.demo.paymentservice.entity.PaymentEntity;
import com.boris.demo.paymentservice.model.Payment;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface PaymentMapper {

    Payment paymentEntityToPayment(PaymentEntity paymentEntity );
}
