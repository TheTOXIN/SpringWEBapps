package com.toixn.mongopring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "countries")
@NoArgsConstructor
public class Country {

    @Id
    private Long id;

    @Indexed(unique = true)
    private String name;

    @Field(value = "count_people")
    private int population = 0;

}
