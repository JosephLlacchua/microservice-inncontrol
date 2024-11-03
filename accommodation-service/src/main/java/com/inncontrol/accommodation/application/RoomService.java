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
 * Room service class for managing room operations.
 * @author Sharon Antuanet Ivet Barrial Marin - u202114900
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
        RoomType roomType = RoomType.fromString(roomDTO.getRoomType());
        RoomStatus roomStatus = RoomStatus.valueOf(roomDTO.getRoomStatus().toUpperCase());
        Room room = new Room(roomDTO.getRoomNumber(), roomType);
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomType);
        room.setRoomStatus(roomStatus);

        return roomRepository.save(room);
    }
    public Optional<Room> findRoomByRoomNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public Optional<Room> updateRoom(int roomNumber, RoomDTO roomDTO) {
        Optional<Room> roomOptional = findRoomByRoomNumber(roomNumber);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.setRoomNumber(roomDTO.getRoomNumber());
            room.setRoomType(RoomType.fromString(roomDTO.getRoomType()));
            return Optional.of(roomRepository.save(room));
        }
        return Optional.empty();
    }

    public List<Room> getRoomsByRoomType(String roomType) {
        return roomRepository.findByRoomType(RoomType.fromString(roomType));
    }

    public List<Room> getRoomsByRoomStatus(String roomStatus) {
        return roomRepository.findByRoomStatus(RoomStatus.valueOf(roomStatus.toUpperCase()));
    }
}