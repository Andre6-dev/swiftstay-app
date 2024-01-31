package com.devandre.swiftstay.services;

import com.devandre.swiftstay.dao.RoomDao;
import com.devandre.swiftstay.dto.ListRoomDto;
import com.devandre.swiftstay.dto.RoomDto;
import com.devandre.swiftstay.dto.mappers.ListRoomMapper;
import com.devandre.swiftstay.dto.mappers.RoomMapper;
import com.devandre.swiftstay.exception.common.ResourceNotFoundException;
import com.devandre.swiftstay.persistence.models.Room;
import com.devandre.swiftstay.persistence.models.enums.ERoomType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * andre on 9/01/2024
 */
@Slf4j(topic = "RoomService")
@Service
public class RoomService {

    private final RoomDao roomDao;
    private final ListRoomMapper listRoomMapper;

    private final RoomMapper roomMapper;

    public RoomService(
            @Qualifier("roomDao") RoomDao roomDao,
            ListRoomMapper listRoomMapper, RoomMapper roomMapper) {
        this.roomDao = roomDao;
        this.listRoomMapper = listRoomMapper;
        this.roomMapper = roomMapper;
    }

    public List<ListRoomDto> getAllRooms() {
        log.info("Getting all rooms");
        return roomDao.getAllRooms()
                .stream()
                .map(listRoomMapper::toDto)
                .toList();
    }

    public ListRoomDto getRoomById(UUID id) {
        log.info("Getting room with id [{}]", id);
        return this.roomDao.getRoomById(id)
                .map(listRoomMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id [%s] not found".formatted(id)));
    }

    public List<ERoomType> getDistinctRoomTypes() {
        log.info("Getting all distinct room types");
        return this.roomDao.getDistinctRoomTypes();
    }

    public List<ListRoomDto> getAvailableRoomsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, ERoomType roomType) {
        log.info("Getting all available rooms by dates and type");
        return this.roomDao.getAvailableRoomsByDatesAndType(checkInDate, checkOutDate, roomType)
                .stream()
                .map(listRoomMapper::toDto)
                .toList();
    }

    public RoomDto createRoom(RoomDto roomDto) {
        log.info("Creating room with room type [{}]", roomDto.roomType());

        Room newRoom = roomDao.saveRoom(roomMapper.toEntity(roomDto));

        newRoom.setPhotoUrl("https://images.pexels.com/photos/164595/pexels-photo-164595.jpeg");

        return roomMapper.toDto(newRoom);
    }

    public void deleteRoomById(UUID id) {
        log.info("Deleting room with id [{}]", id);

        if (this.roomDao.getRoomById(id).isEmpty()) {
            throw new ResourceNotFoundException("Room with id [%s] not found".formatted(id));
        }

        this.roomDao.deleteRoomById(id);
    }

    public RoomDto updateRoom(UUID roomId, RoomDto roomDto) {
        log.info("Updating room with id [{}]", roomId);

        if (this.roomDao.getRoomById(roomId).isEmpty()) {
            throw new ResourceNotFoundException("Room with id [%s] not found".formatted(roomId));
        }

        Room room = roomDao.getRoomById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id [%s] not found".formatted(roomId)));

        room.setId(roomId);

        boolean changes = false;

        if (roomDto.name() != null && !roomDto.name().equals(room.getName())) {
            room.setName(roomDto.name());
            changes = true;
        }

        if (roomDto.roomType() != null && !roomDto.roomType().equals(room.getRoomType())) {
            room.setRoomType(roomDto.roomType());
            changes = true;
        }

        if (roomDto.roomPrice() != null && !roomDto.roomPrice().equals(room.getRoomPrice())) {
            room.setRoomPrice(roomDto.roomPrice());
            changes = true;
        }

        if (roomDto.slug() != null && !roomDto.slug().equals(room.getSlug())) {
            room.setSlug(roomDto.slug());
            changes = true;
        }

        if (roomDto.roomDescription() != null && !roomDto.roomDescription().equals(room.getRoomDescription())) {
            room.setRoomDescription(roomDto.roomDescription());
            changes = true;
        }

        if (roomDto.numberOfBeds() != null && !roomDto.numberOfBeds().equals(room.getNumberOfBeds())) {
            room.setNumberOfBeds(roomDto.numberOfBeds());
            changes = true;
        }

        if (roomDto.roomDimension() != null && !roomDto.roomDimension().equals(room.getRoomDimension())) {
            room.setRoomDimension(roomDto.roomDimension());
            changes = true;
        }

        if (roomDto.isBooked() != null && !roomDto.isBooked().equals(room.getIsBooked())) {
            room.setIsBooked(roomDto.isBooked());
            changes = true;
        }

        if (roomDto.isFeatured() != null && !roomDto.isFeatured().equals(room.getIsFeatured())) {
            room.setIsFeatured(roomDto.isFeatured());
            changes = true;
        }

        if (!changes) {
            throw new RuntimeException("No changes detected");
        }

        return roomMapper.toDto(this.roomDao.updateRoom(room));
    }


}
