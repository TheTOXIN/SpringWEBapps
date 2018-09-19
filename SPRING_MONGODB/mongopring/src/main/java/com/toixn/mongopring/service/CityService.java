package com.toixn.mongopring.service;

import com.toixn.mongopring.entity.City;
import com.toixn.mongopring.entity.Person;
import com.toixn.mongopring.repository.CityRepository;
import com.toixn.mongopring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CityService {

    private final CityRepository cityRepository;

    private final PersonRepository personRepository;

    @Autowired
    public CityService(
        CityRepository cityRepository,
        PersonRepository personRepository
    ) {
        this.cityRepository = cityRepository;
        this.personRepository = personRepository;
    }

    public void save(City city) {
        cityRepository.save(city);
    }

    public City find(Long id) {
        City city = cityRepository.findById(id).orElse(null);

        if (city == null) throw new RuntimeException("CITY NOT FOUND");

        List<Person> people = personRepository.findByCity(city);

        city.getPeople().addAll(people);

        return city;
    }
}
