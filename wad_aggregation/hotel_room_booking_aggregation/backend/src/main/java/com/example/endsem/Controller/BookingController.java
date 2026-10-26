package com.example.endsem.Controller;
import com.example.endsem.Model.Room;
import com.example.endsem.Model.Booking;
import com.example.endsem.Service.BookingService;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api") public class BookingController {
 private final BookingService service;
 public BookingController(BookingService service){this.service=service;}
 @PostMapping("/rooms") public Room addRoom(@RequestBody Room r){return service.addRoom(r);}
 @GetMapping("/rooms") public List<Room> getRooms(){return service.getRooms();}
 @GetMapping("/rooms/available") public List<Room> getAvailableRooms(){return service.getAvailableRooms();}
 @PostMapping("/bookings") public Booking bookRoom(@RequestBody Booking b){return service.bookRoom(b);}
 @GetMapping("/bookings") public List<Booking> getBookings(){return service.getBookings();}
 @GetMapping("/room-statistics") public List<Document> getRoomStatistics(){return service.getRoomStatistics();}
 @GetMapping("/booking-details") public List<Document> getBookingDetails(){return service.getBookingDetails();}
}
