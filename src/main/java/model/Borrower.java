//package model;
//
//import java.sql.Date;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "Borrower")
//public class Borrower {
//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private Book book;
//    
//    @Column(name = "due_date")
//    private Date due_date;
//    
//    @Column(name = "fine")
//    private Integer fine;
//    
//    @Id
//    @Column(name = "id")
//    private UUID id=UUID.randomUUID();
//    
//    @Column(name = "late_charge_fees")
//    private Integer late_charge_fees;
//    
//    @Column(name = "pickup_date")
//    private Date pickup_date;
//    
//    public Borrower(Book book, Date due_date, Integer fine, UUID id, Integer late_charge_fees, Date pickup_date,
//			User reader, Date return_date) {
//		super();
//		this.book = book;
//		this.due_date = due_date;
//		this.fine = fine;
//		this.id = id;
//		this.late_charge_fees = late_charge_fees;
//		this.pickup_date = pickup_date;
//		this.reader = reader;
//		this.return_date = return_date;
//	}
//
//	public Book getBook() {
//		return book;
//	}
//
//	public void setBook(Book book) {
//		this.book = book;
//	}
//
//	public Date getDue_date() {
//		return due_date;
//	}
//
//	public void setDue_date(Date due_date) {
//		this.due_date = due_date;
//	}
//
//	public Integer getFine() {
//		return fine;
//	}
//
//	public void setFine(Integer fine) {
//		this.fine = fine;
//	}
//
//	public UUID getId() {
//		return id;
//	}
//
//	public void setId(UUID id) {
//		this.id = id;
//	}
//
//	public Integer getLate_charge_fees() {
//		return late_charge_fees;
//	}
//
//	public void setLate_charge_fees(Integer late_charge_fees) {
//		this.late_charge_fees = late_charge_fees;
//	}
//
//	public Date getPickup_date() {
//		return pickup_date;
//	}
//
//	public void setPickup_date(Date pickup_date) {
//		this.pickup_date = pickup_date;
//	}
//
//	public User getReader() {
//		return reader;
//	}
//
//	public void setReader(User reader) {
//		this.reader = reader;
//	}
//
//	public Date getReturn_date() {
//		return return_date;
//	}
//
//	public void setReturn_date(Date return_date) {
//		this.return_date = return_date;
//	}
//
//	@ManyToOne
//    @JoinColumn(name = "reader_id")
//    private User reader;
//    
//    @Column(name = "return_date")
//    private Date return_date;
//}
