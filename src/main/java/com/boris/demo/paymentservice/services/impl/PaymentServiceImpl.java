package com.boris.demo.paymentservice.services.impl;

import com.boris.demo.paymentservice.entity.PaymentEntity;
import com.boris.demo.paymentservice.entity.PaymentMethodEntity;
import com.boris.demo.paymentservice.entity.UserEntity;
import com.boris.demo.paymentservice.enums.PaymentStatusEnum;
import com.boris.demo.paymentservice.mappers.PaymentMapper;
import com.boris.demo.paymentservice.model.PageDTO;
import com.boris.demo.paymentservice.model.Payment;
import com.boris.demo.paymentservice.model.PaymentRequest;
import com.boris.demo.paymentservice.model.User;
import com.boris.demo.paymentservice.repository.JpaPaymentEntity;
import com.boris.demo.paymentservice.repository.JpaPaymentMethodRepository;
import com.boris.demo.paymentservice.repository.JpaUserRepository;
import com.boris.demo.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Currency;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Autowired
    JpaPaymentMethodRepository jpaPaymentMethodRepository;

    @Autowired
    JpaPaymentEntity jpaPaymentEntity;

    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public UUID initializePayment(PaymentRequest paymentRequest) {
        UserEntity user = jpaUserRepository.findById( paymentRequest.getUserId() ).get();
        UserEntity payee = jpaUserRepository.findById( paymentRequest.getPayeeId() ).get();
        PaymentMethodEntity paymentMethodEntity = jpaPaymentMethodRepository.findById( paymentRequest.getPaymentMethod() ).get();
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount( paymentRequest.getAmount() );
        paymentEntity.setCurrency( paymentRequest.getCurrency() );
        paymentEntity.setPaymentStatus(PaymentStatusEnum.PENDING );
        paymentEntity.setUser( user );
        paymentEntity.setPayee( payee );
        paymentEntity.setPaymentMethod( paymentMethodEntity );
        paymentEntity = jpaPaymentEntity.save( paymentEntity );
        return paymentEntity.getId();
    }

    @Override
    public PageDTO<Payment> getPayments(int page, int size) {
        PageDTO<Payment> pageDTO = new PageDTO<>();
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "lastModifiedDate" );
        Page<PaymentEntity> pageEntity = jpaPaymentEntity.findAll( pageRequest );
        pageDTO.setPageNumber( pageEntity.getNumber() );
        pageDTO.setTotalElements( pageEntity.getTotalElements() );
        pageDTO.setTotalPages( pageEntity.getTotalPages() );
        List<Payment> list;
        List<PaymentEntity> paymentEntities = pageEntity.getContent();
        if ( paymentEntities!=null ){
            list = paymentEntities.stream().map( p -> paymentMapper.paymentEntityToPayment( p ) ).collect( Collectors.toList() );
            pageDTO.setContent( list );
        }
        return pageDTO;
    }

    @Override
    public Payment getPaymentById(UUID paymentId) {
        var o = jpaPaymentEntity.findById( paymentId );
        if ( o.isEmpty() ){
            throw new HttpClientErrorException( HttpStatus.NO_CONTENT, "not found payment by id: " +paymentId );
        }
        return paymentMapper.paymentEntityToPayment( o.get() );
    }


}
