package com.example.endsem.Repository;
import com.example.endsem.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface EmployeeRepository extends MongoRepository<Employee,String> {  }
