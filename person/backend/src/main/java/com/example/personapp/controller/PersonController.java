package com.example.personapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.personapp.model.Person;
import com.example.personapp.repository.PersonRepository;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonRepository repo;

    public PersonController(PersonRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Person> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable String id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Person add(@RequestBody Person p) {
        p.id = null;
        return repo.save(p);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable String id, @RequestBody Person p) {
        p.id = id;
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
