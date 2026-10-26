package com.example.endsem.Service;
import com.example.endsem.Model.Room;
import com.example.endsem.Model.Booking;
import com.example.endsem.Repository.RoomRepository;
import com.example.endsem.Repository.BookingRepository;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;
import java.util.List;
@Service public class BookingService {
 private final RoomRepository roomRepo; private final BookingRepository bookingRepo; private final MongoTemplate mongoTemplate;
 public BookingService(RoomRepository roomRepo,BookingRepository bookingRepo,MongoTemplate mongoTemplate){this.roomRepo=roomRepo;this.bookingRepo=bookingRepo;this.mongoTemplate=mongoTemplate;}
 public Room addRoom(Room room){room.status="AVAILABLE";return roomRepo.save(room);}
 public List<Room> getRooms(){return roomRepo.findAll();}
 public List<Room> getAvailableRooms(){return roomRepo.findByStatus("AVAILABLE");}
 public Booking bookRoom(Booking booking){
  Room room=roomRepo.findById(booking.roomId).orElse(null);
  if(room==null || !"AVAILABLE".equals(room.status))return null;
  if(booking.bookingID!=null && bookingRepo.existsById(booking.bookingID))return null;
  booking.bookingStatus="CONFIRMED";room.status="BOOKED";roomRepo.save(room);return bookingRepo.save(booking);
 }
 public List<Booking> getBookings(){return bookingRepo.findAll();}
 public List<Document> getRoomStatistics(){
  Aggregation agg=Aggregation.newAggregation(Aggregation.group("roomType").count().as("totalRooms").avg("price").as("averagePrice").sum("price").as("totalPrice").min("price").as("minimumPrice").max("price").as("maximumPrice"));
  return mongoTemplate.aggregate(agg,"rooms",Document.class).getMappedResults();
 }
 public List<Document> getBookingDetails(){
  Aggregation agg=Aggregation.newAggregation(Aggregation.lookup("rooms","roomId","_id","room"),Aggregation.unwind("room"));
  return mongoTemplate.aggregate(agg,"bookings",Document.class).getMappedResults();
 }
}
