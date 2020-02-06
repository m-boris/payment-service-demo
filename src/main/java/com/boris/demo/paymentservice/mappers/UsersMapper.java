package com.boris.demo.paymentservice.mappers;

import com.boris.demo.paymentservice.entity.UserEntity;
import com.boris.demo.paymentservice.model.User;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface UsersMapper {

    User userEntityToUser(UserEntity userEntity);

    UserEntity userToUserEntity( User user );

}
