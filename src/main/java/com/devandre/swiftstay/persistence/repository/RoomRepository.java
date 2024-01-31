package com.devandre.swiftstay.persistence.repository;

import com.devandre.swiftstay.persistence.models.Room;
import com.devandre.swiftstay.persistence.models.enums.ERoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {

    @Query("SELECT DISTINCT r.roomType FROM Room r")
    List<ERoomType> findDistinctRoomTypes();

    @Query("SELECT r FROM Room r " +
            "WHERE r.roomType = :roomType " +
            "AND r.id NOT IN " +
            "(SELECT br.room.id FROM BookedRoom br " +
            " WHERE ((br.checkInDate <= :checkOutDate) AND (br.checkOutDate >= :checkInDate))" +
            ")"
    )
    List<Room> findAvailableRoomsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, ERoomType roomType);
}