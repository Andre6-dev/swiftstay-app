package com.devandre.swiftstay.dao;

import com.devandre.swiftstay.persistence.models.Room;
import com.devandre.swiftstay.persistence.models.enums.ERoomType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * andre on 9/01/2024
 */
public interface RoomDao {

    List<Room> getAllRooms();

    List<ERoomType> getDistinctRoomTypes();

    List<Room> getAvailableRoomsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, ERoomType roomType);

    Optional<Room> getRoomById(UUID id);

    Room saveRoom(Room room);

    void deleteRoomById(UUID id);

    Room updateRoom(Room room);

}
