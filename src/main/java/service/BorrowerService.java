package service;

import dao.BorrowerDAO;
import model.Borrower;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class BorrowerService {
    private BorrowerDAO borrowerDAO;
    
    public BorrowerService() {
        this.borrowerDAO = new BorrowerDAO();
    }
    
    public void createBorrower(Borrower borrower) throws Exception {
        // Add business logic validation
        if (borrower.getBook() == null) {
            throw new Exception("Book cannot be null");
        }
        if (borrower.getReader() == null) {
            throw new Exception("Reader cannot be null");
        }
        if (borrower.getPickup_date() == null) {
            throw new Exception("Pickup date cannot be null");
        }
        if (borrower.getDue_date() == null) {
            throw new Exception("Due date cannot be null");
        }
        
        // Validate dates
        if (borrower.getDue_date().before(borrower.getPickup_date())) {
            throw new Exception("Due date cannot be before pickup date");
        }
        
        borrowerDAO.saveBorrower(borrower);
    }
    
    public void updateBorrower(Borrower borrower) throws Exception {
        // Check if borrower exists
        Borrower existingBorrower = borrowerDAO.getBorrowerById(borrower.getId());
        if (existingBorrower == null) {
            throw new Exception("Borrower not found");
        }
        
        // Add validation similar to create
        if (borrower.getBook() == null || borrower.getReader() == null) {
            throw new Exception("Book and Reader cannot be null");
        }
        
        borrowerDAO.saveBorrower(borrower);
    }
    
    public void deleteBorrower(UUID borrowerId) throws Exception {
        Borrower borrower = borrowerDAO.getBorrowerById(borrowerId);
        if (borrower != null) {
            borrowerDAO.deleteBorrower(borrower);
        } else {
            throw new Exception("Borrower not found");
        }
    }
    
    public Borrower getBorrowerById(UUID id) {
        return borrowerDAO.getBorrowerById(id);
    }
    
    public List<Borrower> getAllBorrowers() {
        return borrowerDAO.getAllBorrowers();
    }
    
    public List<Borrower> getBorrowersByReaderId(UUID readerId) {
        return borrowerDAO.getBorrowersByReaderId(readerId);
    }
    
    public List<Borrower> getBorrowersByBookId(UUID bookId) {
        return borrowerDAO.getBorrowersByBookId(bookId);
    }
    
    public void returnBook(UUID borrowerId, Date returnDate) throws Exception {
        Borrower borrower = borrowerDAO.getBorrowerById(borrowerId);
        if (borrower == null) {
            throw new Exception("Borrower not found");
        }
        
        borrower.setReturn_date(returnDate);
        
        // Calculate late fees if applicable
        if (returnDate.after(borrower.getDue_date())) {
            long daysLate = (returnDate.getTime() - borrower.getDue_date().getTime()) / (1000 * 60 * 60 * 24);
            borrower.setLate_charge_fees((int)(daysLate * 10)); // Assuming $10 per day late fee
        }
        
        borrowerDAO.saveBorrower(borrower);
    }
}
