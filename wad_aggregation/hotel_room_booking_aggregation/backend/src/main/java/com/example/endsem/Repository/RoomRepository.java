package com.example.endsem.Repository;
import com.example.endsem.Model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface RoomRepository extends MongoRepository<Room,String> { List<Room> findByStatus(String status); }
