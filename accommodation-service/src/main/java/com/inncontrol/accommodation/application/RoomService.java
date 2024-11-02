package com.inncontrol.accommodation.application;

import com.inncontrol.accommodation.domain.Room;
import com.inncontrol.accommodation.domain.RoomRepository;
import com.inncontrol.accommodation.dto.RoomDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
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
        Room room = new Room(roomDTO.getRoomNumber(), roomDTO.getRoomType(), roomDTO.getRoomStatus());
        return roomRepository.save(room);
    }
}
