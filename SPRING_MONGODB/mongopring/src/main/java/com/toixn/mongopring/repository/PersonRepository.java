package com.toixn.mongopring.repository;

import com.toixn.mongopring.entity.City;
import com.toixn.mongopring.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends MongoRepository<Person, Long> {

    Person findByName(String name);
    List<Person> findByCity(City city);

}
