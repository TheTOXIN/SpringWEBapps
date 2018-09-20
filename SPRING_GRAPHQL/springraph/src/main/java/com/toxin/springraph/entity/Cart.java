package com.toxin.springraph.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer capacity;

}
