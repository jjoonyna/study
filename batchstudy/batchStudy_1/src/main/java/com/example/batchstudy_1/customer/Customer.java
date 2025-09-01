package com.example.batchstudy_1.customer;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private LocalDateTime createAt;

    @Setter
    private LocalDateTime loginAt;

    @Setter
    private Status status;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.createAt = LocalDateTime.now();
        this.loginAt = LocalDateTime.now();
        this.status = Status.NOMAL;
    }
    public enum Status{
        NOMAL,
        DORMANT;
    }
}
