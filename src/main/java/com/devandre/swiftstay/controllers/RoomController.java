package com.devandre.swiftstay.controllers;

import com.devandre.swiftstay.controllers.response.ResponseHandler;
import com.devandre.swiftstay.dto.RoomDto;
import com.devandre.swiftstay.persistence.models.enums.ERoomType;
import com.devandre.swiftstay.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

import static com.devandre.swiftstay.utils.Constants.API_BASE_PATH;

/**
 * andre on 10/01/2024
 */
@RestController
@RequestMapping(API_BASE_PATH + "rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllRooms() {
        return ResponseHandler.response(HttpStatus.OK, roomService.getAllRooms(), true);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Object> getRoomById(@PathVariable("roomId") UUID roomId) {
        return ResponseHandler.response(HttpStatus.OK, roomService.getRoomById(roomId), true);
    }

    @GetMapping("/types")
    public ResponseEntity<Object> getAllRoomTypes() {
        return ResponseHandler.response(HttpStatus.OK, roomService.getDistinctRoomTypes(), true);
    }

    @GetMapping("/available")
    public ResponseEntity<Object> getAllAvailableRooms(
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam("roomType") ERoomType roomType
    ) {
        return ResponseHandler.response(HttpStatus.OK, roomService.getAvailableRoomsByDatesAndType(checkInDate, checkOutDate, roomType), true);
    }

    @PostMapping
    public ResponseEntity<Object> createRoom(@Valid @RequestBody RoomDto roomDto) {
        return ResponseHandler.response(HttpStatus.OK, roomService.createRoom(roomDto), true);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Object> deleteRoom(@PathVariable("roomId") UUID roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseHandler.response(HttpStatus.OK, "Room deleted successfully", true);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<Object> updateRoom(@PathVariable("roomId") UUID roomId, @Valid @RequestBody RoomDto roomDto) {
        return ResponseHandler.response(HttpStatus.OK, roomService.updateRoom(roomId, roomDto), true);
    }

}
