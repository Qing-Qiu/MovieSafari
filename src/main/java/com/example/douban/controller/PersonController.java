package com.example.douban.controller;

import com.example.douban.pojo.Person;
import com.example.douban.service.PersonService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/person")
public class PersonController {
    @Resource
    PersonService personService;

    @PostMapping("/detail")
    public ResponseEntity<Person> handleDetailPage(@RequestBody Map<String, String> personData) {
        try {
            Person person = personService.findPersonById(personData.get("id"));
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/relevant")
    public ResponseEntity<ArrayList<Person>> handleRelevantPage(@RequestBody Map<String, String> personData) {
        try {
            ArrayList<Person> persons = personService.findPersonByMovie(personData.get("id"));
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }
}
