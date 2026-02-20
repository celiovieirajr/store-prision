package com.example.prision.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerRecipient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;

    @Embedded
    private Address address;
}
