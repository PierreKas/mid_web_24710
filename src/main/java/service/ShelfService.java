//package service;
//
//
//import dao.ShelfDao;
//
//public class ShelfService {
//    private ShelfDao shelfDao;
//
//    public ShelfService() {
//        shelfDao = new ShelfDao();
//    }
//
//    public boolean assignBookToShelf(int bookId, int shelfId) {
//        return shelfDao.assignBookToShelf(bookId, shelfId);
//    }
//
//    public boolean assignShelfToRoom(int shelfId, int roomId) {
//        return shelfDao.assignShelfToRoom(shelfId, roomId);
//    }
//
//    public int countBooksInRoom(int roomId) {
//        return shelfDao.countBooksInRoom(roomId);
//    }
//}
//
