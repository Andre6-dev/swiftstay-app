package com.devandre.swiftstay.persistence.models;

import com.devandre.swiftstay.persistence.models.enums.ERoomType;
import com.devandre.swiftstay.persistence.models.shared.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "rooms")
public class Room extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "room_id" ,updatable = false, nullable = false)
    private UUID id;

    @Column(name = "room_name", nullable = false, length = 100)
    private String name;

    @Column(name = "room_type", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private ERoomType roomType;

    @Column(name = "room_price", nullable = false)
    private BigDecimal roomPrice;

    @Column(name = "room_slug", nullable = false)
    private String slug;

    @Column(name = "room_description", nullable = false)
    private String roomDescription;

    @Column(name = "number_of_beds", nullable = false)
    private Integer numberOfBeds;

    @Column(name = "room_dimension", nullable = false)
    private String roomDimension;

    @Column(name = "is_booked")
    private Boolean isBooked = false;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<BookedRoom> bookedRooms;

    public void addBookedRoom(BookedRoom bookedRoom) {
        if (bookedRooms == null) {
            bookedRooms = new ArrayList<>();
        }
        bookedRooms.add(bookedRoom);
        bookedRoom.setRoom(this);
        isBooked = true;
        String bookingCode = RandomStringUtils.randomNumeric(10);
        bookedRoom.setBookingConfirmationCode(bookingCode);
    }
}
