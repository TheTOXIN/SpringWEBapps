package com.toixn.mongopring.service;

import com.toixn.mongopring.entity.City;
import com.toixn.mongopring.entity.Country;
import com.toixn.mongopring.repository.CityRepository;
import com.toixn.mongopring.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private MongoTemplate mongoTemplate;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;

    @Autowired
    public CountryService(
        MongoTemplate mongoTemplate,
        CountryRepository countryRepository,
        CityRepository cityRepository,
        CityService cityService
    ) {
        this.mongoTemplate = mongoTemplate;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.cityService = cityService;
    }

    public void save(Country country) {
        countryRepository.save(country);
    }

    public List<Country> findAll() {
        List<Country> countries = countryRepository.findAll();

        countries.forEach(this::computeCountPeople);

        return countries;
    }

    private void computeCountPeople(Country country) {
        cityRepository.findByAndCountry(country)
            .stream()
            .mapToInt(c -> cityService.find(c.getId()).getPeople().size())
            .boxed()
            .forEach(n -> updateCountPeople(country, n));
    }

    private void updateCountPeople(Country country, int count) {
        country.setPopulation(count);

        Criteria criteria = Criteria.where("name").is(country.getName());
        Query query = new Query(criteria);

        Update update = new Update();
        update.set("count_people", count);

        mongoTemplate.updateFirst(query, update, Country.class);
    }

}
