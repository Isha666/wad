package com.example.endsem.Controller;
import com.example.endsem.Model.Employee;
import com.example.endsem.Model.Leave;
import com.example.endsem.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api") public class EmployeeController {
 private final EmployeeService service;
 public EmployeeController(EmployeeService service){this.service=service;}
 @PostMapping("/employees") public Employee addEmployee(@RequestBody Employee e){return service.addEmployee(e);}
 @GetMapping("/employees") public List<Employee> getEmployees(){return service.getEmployees();}
 @PostMapping("/leaves") public Leave applyLeave(@RequestBody Leave l){return service.applyLeave(l);}
 @GetMapping("/leaves") public List<Leave> getLeaves(){return service.getLeaves();}
 @GetMapping("/valid-leaves") public List<String> getValidLeaves(){return service.getValidLeaves();}
}
