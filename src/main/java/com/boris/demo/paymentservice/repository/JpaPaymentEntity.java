package com.boris.demo.paymentservice.repository;

import com.boris.demo.paymentservice.entity.PaymentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface JpaPaymentEntity extends PagingAndSortingRepository<PaymentEntity, UUID> {
}
