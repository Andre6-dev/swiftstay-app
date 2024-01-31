package com.devandre.swiftstay.persistence.models.enums;

public enum ERoomType {

    STANDARD("Standard"),
    DELUXE("Deluxe"),
    SUITE("Suite"),
    EXECUTIVE("Executive"),
    FAMILY("Family");

    private final String roomType;

    ERoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }
}
