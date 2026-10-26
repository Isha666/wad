package com.example.endsem.Repository;
import com.example.endsem.Model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface BookingRepository extends MongoRepository<Booking,String> { List<Booking> findByRoomId(String roomId); }
