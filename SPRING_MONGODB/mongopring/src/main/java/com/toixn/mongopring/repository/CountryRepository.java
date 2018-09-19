package com.toixn.mongopring.repository;

import com.toixn.mongopring.entity.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends MongoRepository<Country, Long> {

}
