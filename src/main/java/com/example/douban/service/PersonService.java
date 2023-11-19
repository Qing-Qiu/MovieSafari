package com.example.douban.service;

import com.example.douban.mapper.PersonMapper;
import com.example.douban.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonService {
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public Person findPersonById(String id) {
        return personMapper.findPersonById(id);
    }

    public ArrayList<Person> findPersonByMovie(String id) {
        return personMapper.findPersonByMovie(id);
    }
}
