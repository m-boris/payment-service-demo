package com.boris.demo.paymentservice.repository;

import com.boris.demo.paymentservice.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;


public interface JpaUserRepository extends PagingAndSortingRepository<UserEntity, UUID > {
    Page<UserEntity> findByFullNameIgnoreCaseContaining(String query, Pageable pageable);

    List<UserEntity> findByFullName(String fullName);
}
