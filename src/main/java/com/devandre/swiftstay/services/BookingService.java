package com.devandre.swiftstay.services;

import com.devandre.swiftstay.dao.BookingDao;
import com.devandre.swiftstay.dao.RoomDao;
import com.devandre.swiftstay.dto.BookedRoomDto;
import com.devandre.swiftstay.dto.ListBookedRoomDto;
import com.devandre.swiftstay.dto.mappers.BookedRoomMapper;
import com.devandre.swiftstay.dto.mappers.ListBookedRoomMapper;
import com.devandre.swiftstay.exception.common.ResourceNotFoundException;
import com.devandre.swiftstay.persistence.models.BookedRoom;
import com.devandre.swiftstay.persistence.models.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * andre on 9/01/2024
 */
@Slf4j(topic = "BookingService")
@Service
public class BookingService {

    private final BookingDao bookingDao;
    private final ListBookedRoomMapper listBookedRoomMapper;

    private final RoomDao roomDao;
    private final BookedRoomMapper bookedRoomMapper;

    public BookingService(
            @Qualifier("bookingDao") BookingDao bookingDao,
            @Qualifier("roomDao") RoomDao roomDao,
            ListBookedRoomMapper listBookedRoomMapper, BookedRoomMapper bookedRoomMapper
    ) {
        this.bookingDao = bookingDao;
        this.listBookedRoomMapper = listBookedRoomMapper;
        this.roomDao = roomDao;
        this.bookedRoomMapper = bookedRoomMapper;
    }

    public List<ListBookedRoomDto> getAllBookings() {
        log.info("Getting all bookings");
        return bookingDao.selectAllBookings()
                .stream()
                .map(listBookedRoomMapper::toDto)
                .toList();
    }

    public List<ListBookedRoomDto> getBookingsByUserEmail(String email) {
        log.info("Getting all bookings by user email [{}]", email);
        return bookingDao.selectBookingsByUserEmail(email)
                .stream()
                .map(listBookedRoomMapper::toDto)
                .toList();
    }

    public List<ListBookedRoomDto> getBookingsByRoomId(UUID roomId) {
        log.info("Getting all bookings by room id [{}]", roomId);
        return bookingDao.selectBookingsByRoomId(roomId)
                .stream()
                .map(listBookedRoomMapper::toDto)
                .toList();
    }

    public ListBookedRoomDto getBookingByBookingConfirmationCode(String bookingConfirmationCode) {
        log.info("Getting booking by booking confirmation code [{}]", bookingConfirmationCode);
        return bookingDao.selectByBookingConfirmationCode(bookingConfirmationCode)
                .map(listBookedRoomMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Booking with booking confirmation code [%s] not found".formatted(bookingConfirmationCode)));
    }

    public String createBooking(UUID roomId, BookedRoomDto bookedRoomDto) {
        log.info("Creating booking with booking confirmation code [{}]", bookedRoomDto.bookingConfirmationCode());
        if (bookedRoomDto.checkOutDate().isBefore(bookedRoomDto.checkInDate())) {
            throw new IllegalStateException("Check out date [%s] cannot be before check in date [%s]".formatted(bookedRoomDto.checkOutDate(), bookedRoomDto.checkInDate()));
        }

        Room room = roomDao.getRoomById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id [%s] not found".formatted(roomId)));
        List<BookedRoom> existingBookings = room.getBookedRooms();
        boolean roomIsAvailable = roomIsAvailable(bookedRoomDto, existingBookings);

        if (roomIsAvailable) {
            BookedRoom newBooking = bookingDao.insertBooking(bookedRoomMapper.toEntity(bookedRoomDto));
            room.addBookedRoom(newBooking);
            roomDao.saveRoom(room);
        } else {
            throw new IllegalStateException("Room with id [%s] is not available for the selected dates".formatted(roomId));
        }
        return bookedRoomDto.bookingConfirmationCode();
    }

    private boolean roomIsAvailable(BookedRoomDto bookedRoomDto, List<BookedRoom> existingBookings) {
        return existingBookings.stream().noneMatch(existingBooking ->
                isDateOverlap(bookedRoomDto.checkInDate(), bookedRoomDto.checkOutDate(),
                        existingBooking.getCheckInDate(), existingBooking.getCheckOutDate())
        );
    }
    private boolean isDateOverlap(LocalDate checkInDate1, LocalDate checkOutDate1,
                                  LocalDate checkInDate2, LocalDate checkOutDate2) {
        return checkInDate1.isBefore(checkOutDate2) && checkOutDate1.isAfter(checkInDate2);
    }

    public void deleteBookingById(UUID id) {
        log.info("Deleting booking with id [{}]", id);
        bookingDao.deleteBookingById(id);
    }
}
