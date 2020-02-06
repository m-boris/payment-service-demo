package com.boris.demo.paymentservice.services;

import com.boris.demo.paymentservice.model.PageDTO;
import com.boris.demo.paymentservice.model.PaymentMethod;
import com.boris.demo.paymentservice.model.User;

import java.util.List;
import java.util.UUID;


public interface UserService {
    User createUser(User user);

    User getUserById(UUID id);

    PageDTO<User> findUsersByNameQuery(String query, int page, int size);

    User addPaymentMethod(UUID userId, PaymentMethod paymentMethod);
}
