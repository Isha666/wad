package com.example.personapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
public class Person {
    @Id
    public String id;
    public String name;
    public int age;
}
