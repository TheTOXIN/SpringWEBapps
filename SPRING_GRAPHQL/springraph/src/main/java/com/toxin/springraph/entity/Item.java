package com.toxin.springraph.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Integer price;

    @ManyToOne
    @JoinColumn
    private Cart cart;

}
