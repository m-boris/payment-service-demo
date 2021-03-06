package com.boris.demo.paymentservice.repository;

import com.boris.demo.paymentservice.entity.PaymentMethodEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaPaymentMethodRepository extends CrudRepository<PaymentMethodEntity, UUID> {
}
