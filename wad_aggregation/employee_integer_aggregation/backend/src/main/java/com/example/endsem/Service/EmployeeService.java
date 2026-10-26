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
  Employee e=employeeRepo.findById(leave.employeeId).orElse(null);
  if(e==null)return null;
  if(leave.leaveId!=null && leaveRepo.existsById(leave.leaveId))return null;
  java.time.LocalDate start=java.time.LocalDate.parse(leave.startDate);
  java.time.LocalDate end=java.time.LocalDate.parse(leave.endDate);
  int days=(int)java.time.temporal.ChronoUnit.DAYS.between(start,end)+1;
  if(days<=0){leave.status="REJECTED";return leaveRepo.save(leave);}
  if("sick".equals(leave.leaveType)){
   if(e.sickLeave>=days){e.sickLeave-=days;leave.status="APPROVED";}else leave.status="REJECTED";
  }else if("vacation".equals(leave.leaveType)){
   if(e.vacationLeave>=days){e.vacationLeave-=days;leave.status="APPROVED";}else leave.status="REJECTED";
  }else if("casual".equals(leave.leaveType)){
   if(e.casualLeave>=days){e.casualLeave-=days;leave.status="APPROVED";}else leave.status="REJECTED";
  }else leave.status="REJECTED";
  if("APPROVED".equals(leave.status))employeeRepo.save(e);
  return leaveRepo.save(leave);
 }
 public List<Leave> getLeaves(){return leaveRepo.findAll();}
 public List<String> getValidLeaves(){
  Aggregation agg=Aggregation.newAggregation(Aggregation.lookup("employees","employeeId","_id","employee"),Aggregation.unwind("employee"),Aggregation.match(Criteria.where("status").is("APPROVED")));
  List<Document> result=mongoTemplate.aggregate(agg,"leaves",Document.class).getMappedResults();
  List<String> names=new ArrayList<>();
  for(Document d:result){Document employee=d.get("employee",Document.class);names.add(employee.getString("name"));}
  return names;
 }
}
