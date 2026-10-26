package com.example.endsem.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="rooms") public class Room { @Id public String roomId; public String roomType; public double price; public String status; }
