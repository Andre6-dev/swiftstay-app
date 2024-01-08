package com.devandre.swiftstay.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * andre on 8/01/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "booked_rooms")
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id" ,updatable = false, nullable = false)
    private UUID id;

    @Column(name = "check_in")
    private LocalDate checkInDate;

    @Column(name = "check_out")
    private LocalDate checkOutDate;

    @Column(name = "guest_fullName", nullable = false, length = 100)
    private String guestFullName;

    @Column(name = "guest_email", nullable = false, length = 100)
    private String guestEmail;

    @Column(name = "adults")
    private Integer NumOfAdults;

    @Column(name = "children")
    private Integer NumOfChildren;

    @Column(name = "total_guest")
    private Integer totalNumOfGuest;

    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @ToString.Exclude
    private Room room;

    public void calculateTotalNumberOfGuests() {
        this.totalNumOfGuest = this.NumOfAdults + this.NumOfChildren;
    }

    public void setNumOfAdults(Integer numOfAdults) {
        this.NumOfAdults = numOfAdults;
        calculateTotalNumberOfGuests();
    }

    public void setNumOfChildren(Integer numOfChildren) {
        this.NumOfChildren = numOfChildren;
        calculateTotalNumberOfGuests();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookedRoom that = (BookedRoom) o;
        return Objects.equals(id, that.id) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate) && Objects.equals(guestFullName, that.guestFullName) && Objects.equals(guestEmail, that.guestEmail) && Objects.equals(NumOfAdults, that.NumOfAdults) && Objects.equals(NumOfChildren, that.NumOfChildren) && Objects.equals(totalNumOfGuest, that.totalNumOfGuest) && Objects.equals(bookingConfirmationCode, that.bookingConfirmationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkInDate, checkOutDate, guestFullName, guestEmail, NumOfAdults, NumOfChildren, totalNumOfGuest, bookingConfirmationCode);
    }
}
