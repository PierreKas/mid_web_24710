package service;

import dao.RoomDAO;
import model.Room;

import java.util.List;
import java.util.UUID;

public class RoomService {
    private RoomDAO roomDAO;
    
    public RoomService() {
        this.roomDAO = new RoomDAO();
    }
    
    public void createRoom(Room room) throws Exception {
        // Add business logic validation
        if (room.getRoom_code() == null || room.getRoom_code().trim().isEmpty()) {
            throw new Exception("Room code cannot be empty");
        }
        
        // Check if room code already exists
        Room existingRoom = roomDAO.getRoomByCode(room.getRoom_code());
        if (existingRoom != null) {
            throw new Exception("Room code already exists");
        }
        
        roomDAO.saveRoom(room);
    }
    
    public void updateRoom(Room room) throws Exception {
        // Similar validations as create
        if (room.getRoom_code() == null || room.getRoom_code().trim().isEmpty()) {
            throw new Exception("Room code cannot be empty");
        }
        
        // Check if room exists
        Room existingRoom = roomDAO.getRoomById(room.getRoom_id());
        if (existingRoom == null) {
            throw new Exception("Room not found");
        }
        
        roomDAO.saveRoom(room);
    }
    
    public void deleteRoom(UUID roomId) throws Exception {
        Room room = roomDAO.getRoomById(roomId);
        if (room != null) {
            roomDAO.deleteRoom(room);
        } else {
            throw new Exception("Room not found");
        }
    }
    
    public Room getRoomById(UUID id) {
        return roomDAO.getRoomById(id);
    }
    
    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }
    
    public Room getRoomByCode(String code) {
        return roomDAO.getRoomByCode(code);
    }
    
    public void updateRoomCode(UUID roomId, String newCode) {
        roomDAO.updateRoomCode(roomId, newCode);
    }
}