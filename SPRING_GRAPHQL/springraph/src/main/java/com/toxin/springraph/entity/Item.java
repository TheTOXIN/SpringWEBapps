package com.toxin.springraph.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Integer price;

}
