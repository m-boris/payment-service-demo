package com.boris.demo.paymentservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table( name="users" )
public class UserEntity extends Auditable{
    @Id
    @GeneratedValue(generator="system-uuid")
    private UUID id;

    private String fullName; // simplicity, first name, last name is better

    private String email;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_payment_methods",   joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "payment_method_id") })
    private Set<PaymentMethodEntity> paymentMethods = new HashSet<>();
}
