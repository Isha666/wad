package com.example.personapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.personapp.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {
}
