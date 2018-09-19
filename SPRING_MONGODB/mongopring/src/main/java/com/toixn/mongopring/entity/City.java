package com.toixn.mongopring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "cities")
@NoArgsConstructor
public class City {

    @Id
    private Long id;

    private String name;

    @DBRef
    private Country country;

    private List<Person> people = new ArrayList<>();

}
