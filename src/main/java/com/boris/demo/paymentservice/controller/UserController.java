package com.boris.demo.paymentservice.controller;

import com.boris.demo.paymentservice.model.PageDTO;
import com.boris.demo.paymentservice.model.PaymentMethod;
import com.boris.demo.paymentservice.model.User;
import com.boris.demo.paymentservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping( path = "/user")
    public User createUser( @RequestBody User user ){
        return userService.createUser( user );
    }


    @GetMapping( path = "/user/{id}" )
    public User getUserById(@PathVariable UUID id ){
        return userService.getUserById( id );
    }

    @GetMapping( path = "/user" )
    public PageDTO<User> findUsersByNameQuery(@RequestParam String query,
                                              @RequestParam( required = false, defaultValue = "0") int page,
                                              @RequestParam( required = false, defaultValue = "10") int size ) {
        return userService.findUsersByNameQuery( query, page, size );
    }

    @PatchMapping( path = "user/{id}/add-payment-method" )
    public User addPaymentMethod(@PathVariable(name = "id") UUID userId, @RequestBody PaymentMethod paymentMethod ){
        return userService.addPaymentMethod( userId, paymentMethod );
    }


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleAppException(HttpClientErrorException ex) {
        log.error( "Client error, message: {}", ex.getMessage() );
        return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode() );
    }

}
