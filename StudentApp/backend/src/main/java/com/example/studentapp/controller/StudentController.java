package com.example.studentapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Student> getStudents() {
        return repo.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repo.save(student);
    }
}
