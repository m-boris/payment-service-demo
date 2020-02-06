package com.boris.demo.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private UUID id;
    private String fullName; // simplicity, first name, last name is better
    private String email;
    private List<PaymentMethod> paymentMethods;
    private Date creationDate;
}
