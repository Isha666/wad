package com.example.studentapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.studentapp.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
}
