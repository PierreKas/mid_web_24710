//package service;
//
//import dao.ReturnDao;
//import model.Membership;
//
//public class ReturnService {
//    private ReturnDao returnDao;
//
//    public ReturnService() {
//        returnDao = new ReturnDao();
//    }
//
//    public double returnBook(int bookId, int userId) {
//        Membership membership = returnDao.getUserMembership(userId);
//        int daysLate = returnDao.calculateLateDays(bookId);
//        double fineRate = membership.getFineRate();
//
//        double fine = daysLate * fineRate;
//        returnDao.updateBookStatus(bookId, userId, fine);
//
//        return fine;
//    }
//}
