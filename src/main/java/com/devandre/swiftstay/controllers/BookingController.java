package com.devandre.swiftstay.controllers;

import com.devandre.swiftstay.controllers.response.ResponseHandler;
import com.devandre.swiftstay.dto.BookedRoomDto;
import com.devandre.swiftstay.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.devandre.swiftstay.utils.Constants.API_BASE_PATH;

/**
 * andre on 10/01/2024
 */
@RestController
@RequestMapping(API_BASE_PATH + "bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllBookings() {
        return ResponseHandler.response(HttpStatus.OK, bookingService.getAllBookings(), true);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Object> getAllBookingsByUserEmail(@PathVariable("email") String email) {
        return ResponseHandler.response(HttpStatus.OK, bookingService.getBookingsByUserEmail(email), true);
    }

    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<Object> getBookingByConfirmationCode(@PathVariable("confirmationCode") String confirmationCode) {
        return ResponseHandler.response(HttpStatus.OK, bookingService.getBookingByBookingConfirmationCode(confirmationCode), true);
    }

    @PostMapping("/room/{roomId}")
    public ResponseEntity<Object> createBooking(@PathVariable("roomId") UUID roomId, @RequestBody BookedRoomDto bookedRoomDto) {
        return ResponseHandler.response(HttpStatus.OK, bookingService.createBooking(roomId, bookedRoomDto), true);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Object> deleteBooking(@PathVariable("bookingId") UUID bookingId) {
        bookingService.deleteBookingById(bookingId);
        return ResponseHandler.response(HttpStatus.OK, "Booking cancelled successfully", true);
    }

}
