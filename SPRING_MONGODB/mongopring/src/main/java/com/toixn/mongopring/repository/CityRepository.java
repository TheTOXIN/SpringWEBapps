package com.toixn.mongopring.repository;

import com.toixn.mongopring.entity.City;
import com.toixn.mongopring.entity.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CityRepository extends MongoRepository<City, Long> {

    List<City> findByAndCountry(Country country);

}
