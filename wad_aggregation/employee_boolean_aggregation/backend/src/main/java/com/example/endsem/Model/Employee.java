package com.example.endsem.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="employees") public class Employee { @Id public String employeeId; public String name; public boolean sickLeave; public boolean vacationLeave; public boolean casualLeave; }
