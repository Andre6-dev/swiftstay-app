package com.devandre.swiftstay.dao.impl;

import com.devandre.swiftstay.dao.RoomDao;
import com.devandre.swiftstay.persistence.models.Room;
import com.devandre.swiftstay.persistence.models.enums.ERoomType;
import com.devandre.swiftstay.persistence.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * andre on 9/01/2024
 */
@Repository("roomDao")
@RequiredArgsConstructor
public class IRoomDao implements RoomDao {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<ERoomType> getDistinctRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public List<Room> getAvailableRoomsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, ERoomType roomType) {
        return roomRepository.findAvailableRoomsByDatesAndType(checkInDate, checkOutDate, roomType);
    }

    @Override
    public Optional<Room> getRoomById(UUID id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(UUID id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }
}
