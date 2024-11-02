package com.inncontrol.accommodation.application;

import com.inncontrol.accommodation.domain.Room;
import com.inncontrol.accommodation.domain.RoomRepository;
import com.inncontrol.accommodation.domain.RoomStatus;
import com.inncontrol.accommodation.domain.RoomType;
import com.inncontrol.accommodation.dto.RoomDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Room service class for managing room registrations.
 * @version 1.0
 */

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Room registerRoom(RoomDTO roomDTO) {
        RoomType roomType = RoomType.valueOf(roomDTO.getRoomType().toUpperCase());
        Room room = new Room(roomDTO.getRoomNumber(), roomType);
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomType);

        return roomRepository.save(room);
    }

    public Optional<Room> findRoomByRoomNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public Optional<Room> updateRoom(int roomNumber,RoomDTO roomDTO) {
        return roomRepository.findByRoomNumber(roomNumber)
                .map(room -> {
                    room.setRoomNumber(roomDTO.getRoomNumber());
                    room.setRoomType(RoomType.valueOf(roomDTO.getRoomType().toUpperCase()));
                    return roomRepository.save(room);
                });
    }

    public List<Room> getRoomsByRoomType(String roomType) {
        return roomRepository.findByRoomType(RoomType.valueOf(roomType.toUpperCase()));
    }

    public List<Room> getRoomsByRoomStatus(String roomStatus) {
        return roomRepository.findByRoomStatus(RoomStatus.valueOf(roomStatus.toUpperCase()));
    }
}