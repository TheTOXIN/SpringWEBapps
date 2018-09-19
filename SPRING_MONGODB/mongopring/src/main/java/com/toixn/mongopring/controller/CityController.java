package com.toixn.mongopring.controller;

import com.toixn.mongopring.entity.City;
import com.toixn.mongopring.entity.Country;
import com.toixn.mongopring.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("save")
    public void save(@RequestBody City city) {
        cityService.save(city);
    }

    @GetMapping("find/{id}")
    public City find(@PathVariable() Long id) {
        return cityService.find(id);
    }

}
