package com.boris.demo.paymentservice.services.impl;

import com.boris.demo.paymentservice.entity.PaymentMethodEntity;
import com.boris.demo.paymentservice.entity.UserEntity;
import com.boris.demo.paymentservice.mappers.PaymentMethodMapper;
import com.boris.demo.paymentservice.mappers.UsersMapper;
import com.boris.demo.paymentservice.model.PageDTO;
import com.boris.demo.paymentservice.model.PaymentMethod;
import com.boris.demo.paymentservice.model.User;
import com.boris.demo.paymentservice.repository.JpaUserRepository;
import com.boris.demo.paymentservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Override
    public User createUser(User user) {
        validateUserNotExists(user);
        UserEntity userEntity = usersMapper.userToUserEntity( user );
        userEntity = jpaUserRepository.save( userEntity );
        return usersMapper.userEntityToUser( userEntity );
    }

    private void validateUserNotExists(User user) {
        List<UserEntity> userEntities = jpaUserRepository.findByFullName( user.getFullName() );
        if ( userEntities!=null && userEntities.size()>0 ){
            throw new HttpClientErrorException( HttpStatus.ALREADY_REPORTED, "User with full name already exists" );
        }
    }

    @Override
    public User getUserById(UUID id) {
        var o = jpaUserRepository.findById( id );
        if ( o.isPresent() ) {
            return usersMapper.userEntityToUser(o.get());
        }
        throw new HttpClientErrorException(HttpStatus.NO_CONTENT, "User not found" );
    }

    @Override
    public PageDTO<User> findUsersByNameQuery(String query, int page, int size) {
        PageDTO<User> pageDTO = new PageDTO<>();
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<UserEntity> pageEntity = jpaUserRepository.findByFullNameIgnoreCaseContaining( query, pageRequest );
        pageDTO.setPageNumber( pageEntity.getNumber() );
        pageDTO.setTotalElements( pageEntity.getTotalElements() );
        pageDTO.setTotalPages( pageEntity.getTotalPages() );
        List<User> list;
        List<UserEntity> userEntities = pageEntity.getContent();
        if ( userEntities!=null ){
            list = userEntities.stream().map( us -> usersMapper.userEntityToUser( us ) ).collect( Collectors.toList() );
            pageDTO.setContent( list );
        }
        return pageDTO;
    }

    @Override
    public User addPaymentMethod(UUID userId, PaymentMethod paymentMethod) {
        var o = jpaUserRepository.findById( userId );
        if ( !o.isPresent() ) {
            throw new HttpClientErrorException(HttpStatus.NO_CONTENT, "User not found" );
        }
        UserEntity userEntity = o.get();
        PaymentMethodEntity paymentMethodEntity = paymentMethodMapper.paymentMethodToPaymentMethodEntity( paymentMethod );
        userEntity.getPaymentMethods().add( paymentMethodEntity );
        userEntity = jpaUserRepository.save( userEntity );
        return usersMapper.userEntityToUser( userEntity );
    }
}
