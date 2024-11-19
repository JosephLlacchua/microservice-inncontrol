package com.inncontrol.accommodation.domain;

/**
 * @author Sharon Antuanet Ivet Barrial Marin - u202114900
 * @version 1.0
 */
public enum RoomType {
    DELUXE,
    STANDARD,
    SUITE;

    public static RoomType fromString(String roomType) {
        try {
            return RoomType.valueOf(roomType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }
}
