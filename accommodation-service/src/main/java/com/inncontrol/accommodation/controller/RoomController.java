package com.inncontrol.accommodation.controller;

import com.inncontrol.accommodation.application.RoomService;
import com.inncontrol.accommodation.domain.Room;
import com.inncontrol.accommodation.dto.RoomDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Room controller class for managing room endpoints.
 * @author Sharon Antuanet Ivet Barrial Marin - u202114900
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/room")
@Tag(name = "Room", description = "Operations for Room API Service")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Operation(summary = "Get all rooms")
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @Operation(summary = "Get a room by room number")
    @GetMapping("/{roomNumber}")
    public ResponseEntity<Room> getRoomByRoomNumber(@PathVariable int roomNumber) {
        return roomService.findRoomByRoomNumber(roomNumber)
                .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all rooms by room type")
    @GetMapping("/roomType/{roomType}")
    public ResponseEntity<List<Room>> getRoomsByRoomType(@PathVariable String roomType) {
        return new ResponseEntity<>(roomService.getRoomsByRoomType(roomType), HttpStatus.OK);
    }

    @Operation(summary = "Get all rooms by room status")
    @GetMapping("/roomStatus/{roomStatus}")
    public ResponseEntity<List<Room>> getRoomsByRoomStatus(@PathVariable String roomStatus) {
        return new ResponseEntity<>(roomService.getRoomsByRoomStatus(roomStatus), HttpStatus.OK);
    }

    @Operation(summary = "Update a room by room number")
    @PutMapping("/update/{roomNumber}")
    public ResponseEntity<Room> updateRoom(@PathVariable int roomNumber, @RequestBody RoomDTO roomDTO) {
        return roomService.updateRoom(roomNumber, roomDTO)
                .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Register a new room")
    @PostMapping("/register")
    public ResponseEntity<Room> registerRoom(@RequestBody RoomDTO roomDTO) {
        Room newRoom = roomService.registerRoom(roomDTO);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }
}