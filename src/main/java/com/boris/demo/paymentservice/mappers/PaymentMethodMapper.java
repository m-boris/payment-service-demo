package com.boris.demo.paymentservice.mappers;

import com.boris.demo.paymentservice.entity.PaymentMethodEntity;
import com.boris.demo.paymentservice.model.PaymentMethod;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodEntity paymentMethodToPaymentMethodEntity( PaymentMethod paymentMethod );

    PaymentMethod paymentMethodEntityToPaymentMethod( PaymentMethodEntity paymentMethodEntity );
}
