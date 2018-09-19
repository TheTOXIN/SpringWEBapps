package com.toixn.mongopring.service;

import com.toixn.mongopring.entity.Person;
import com.toixn.mongopring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public Person findByName(String name) {
        return personRepository.findByName(name);
    }

}
