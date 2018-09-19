package com.toixn.mongopring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "people")
@NoArgsConstructor
public class Person {

    @Id
    private Long id;

    private String name;

    private String profession;

    @DBRef
    private City city;

}
