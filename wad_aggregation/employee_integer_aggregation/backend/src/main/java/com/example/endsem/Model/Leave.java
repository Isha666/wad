package com.example.endsem.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="leaves") public class Leave { @Id public String leaveId; public String employeeId; public String startDate; public String endDate; public String leaveType; public String status; }
