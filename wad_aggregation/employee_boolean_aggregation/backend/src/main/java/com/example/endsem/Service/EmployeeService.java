package com.example.endsem.Service;
import com.example.endsem.Model.Employee;
import com.example.endsem.Model.Leave;
import com.example.endsem.Repository.EmployeeRepository;
import com.example.endsem.Repository.LeaveRepository;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service public class EmployeeService {
 private final EmployeeRepository employeeRepo; private final LeaveRepository leaveRepo; private final MongoTemplate mongoTemplate;
 public EmployeeService(EmployeeRepository employeeRepo,LeaveRepository leaveRepo,MongoTemplate mongoTemplate){this.employeeRepo=employeeRepo;this.leaveRepo=leaveRepo;this.mongoTemplate=mongoTemplate;}
 public Employee addEmployee(Employee e){return employeeRepo.save(e);}
 public List<Employee> getEmployees(){return employeeRepo.findAll();}
 public Leave applyLeave(Leave leave){
  if(!employeeRepo.existsById(leave.employeeId))return null;
  if(leave.leaveId!=null && leaveRepo.existsById(leave.leaveId))return null;
  return leaveRepo.save(leave);
 }
 public List<Leave> getLeaves(){return leaveRepo.findAll();}
 public List<String> getValidLeaves(){
  Aggregation agg=Aggregation.newAggregation(
   Aggregation.lookup("employees","employeeId","_id","employee"),
   Aggregation.unwind("employee"),
   Aggregation.match(new Criteria().orOperator(
    new Criteria().andOperator(Criteria.where("leaveType").is("sick"),Criteria.where("employee.sickLeave").is(true)),
    new Criteria().andOperator(Criteria.where("leaveType").is("vacation"),Criteria.where("employee.vacationLeave").is(true)),
    new Criteria().andOperator(Criteria.where("leaveType").is("casual"),Criteria.where("employee.casualLeave").is(true))
   ))
  );
  List<Document> result=mongoTemplate.aggregate(agg,"leaves",Document.class).getMappedResults();
  List<String> names=new ArrayList<>();
  for(Document d:result){Document employee=d.get("employee",Document.class);names.add(employee.getString("name"));}
  return names;
 }
}
