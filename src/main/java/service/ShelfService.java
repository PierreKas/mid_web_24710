package service;

import java.util.List;
import java.util.UUID;
import dao.ShelfDAO;
import model.Shelf;

public class ShelfService {
    private ShelfDAO shelfDAO;

    public ShelfService() {
        this.shelfDAO = new ShelfDAO();
    }

    public void createShelf(Shelf shelf) throws Exception {
        // Add business logic validation
        if (shelf.getBook_category() == null || shelf.getBook_category().trim().isEmpty()) {
            throw new Exception("Book category cannot be empty");
        }

        // Check if shelf with the same book category already exists in the same room
//        Shelf existingShelf = shelfDAO.getShelfByRoomAndCategory(shelf.getRoom_id(), shelf.getBook_category());
//        if (existingShelf != null) {
//            throw new Exception("Shelf with the same book category already exists in this room");
//        }

        shelfDAO.saveShelf(shelf);
    }

    public void updateShelf(Shelf shelf) throws Exception {
        // Add business logic validation
        if (shelf.getBook_category() == null || shelf.getBook_category().trim().isEmpty()) {
            throw new Exception("Book category cannot be empty");
        }

        // Check if shelf exists
        Shelf existingShelf = shelfDAO.getShelfById(shelf.getId());
        if (existingShelf == null) {
            throw new Exception("Shelf not found");
        }

        shelfDAO.saveShelf(shelf);
    }

    public void deleteShelf(UUID shelfId) throws Exception {
        Shelf shelf = shelfDAO.getShelfById(shelfId);
        if (shelf != null) {
            shelfDAO.deleteShelf(shelf);
        } else {
            throw new Exception("Shelf not found");
        }
    }

    public Shelf getShelfById(UUID id) {
        return shelfDAO.getShelfById(id);
    }

    public List<Shelf> getAllShelves() {
        return shelfDAO.getAllShelves();
    }

//    public Shelf getShelfByRoomAndCategory(UUID roomId, String category) {
//        return shelfDAO.getShelfByRoomAndCategory(roomId, category);
//    }
}