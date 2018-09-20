package com.toxin.springraph.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer capacity;

    @OneToMany(mappedBy = "cart")
    private List<Item> items = new ArrayList<>();

}
