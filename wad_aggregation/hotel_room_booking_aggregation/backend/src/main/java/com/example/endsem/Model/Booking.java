package com.example.endsem.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="bookings") public class Booking { @Id public String bookingID; public String customerName; public String phone; public String roomId; public String bookingStatus; }
