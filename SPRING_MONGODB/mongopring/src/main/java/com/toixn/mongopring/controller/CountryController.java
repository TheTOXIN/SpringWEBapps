package com.toixn.mongopring.controller;

import com.toixn.mongopring.entity.Country;
import com.toixn.mongopring.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("save")
    public void save(@RequestBody Country country) {
        countryService.save(country);
    }

    @GetMapping("findAll")
    public List<Country> findAll() {
        return countryService.findAll();
    }

}
