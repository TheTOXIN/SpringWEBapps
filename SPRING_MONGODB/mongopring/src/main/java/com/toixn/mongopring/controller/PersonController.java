package com.toixn.mongopring.controller;

import com.toixn.mongopring.entity.Person;
import com.toixn.mongopring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("save")
    public void save(@RequestBody Person person) {
        personService.save(person);
    }

    @PostMapping("find-by-name")
    public Person findByName(@RequestBody String name) {
        return personService.findByName(name);
    }

}
