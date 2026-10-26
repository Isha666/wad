package com.example.endsem.Repository;
import com.example.endsem.Model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface LeaveRepository extends MongoRepository<Leave,String> { List<Leave> findByEmployeeId(String employeeId); }
