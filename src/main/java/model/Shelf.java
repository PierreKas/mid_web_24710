//package model;
//import java.util.List;
//import java.util.UUID;
//import javax.persistence.*;
//
//@Entity
//@Table(name = "shelf")
//public class Shelf {
//    @Id
//    @Column(name = "id")
//    private UUID id = UUID.randomUUID();
//    
//    @Column(name = "available_stock")
//    private Integer available_stock;
//    
//    @Column(name = "book_category")
//    private String book_category;
//    
//    @Column(name = "borrowed_number")
//    private Integer borrowed_number;
//    
//    @Column(name = "initial_stock")
//    private Integer initial_stock;
//    
//    @Column(name = "room_id")
//    private UUID room_id;
//    
//    // ManyToOne relationship with Room
//    @ManyToOne
//    @JoinColumn(name = "room_id", insertable = false, updatable = false)
//    private Room room;
//    
//    // OneToMany relationship with Book
//    @OneToMany(mappedBy = "shelf")
//    private List<Book> books;
//
//    // Add id to constructor
//    public Shelf(UUID id, Integer available_stock, String book_category, Integer borrowed_number, Integer initial_stock,
//            UUID room_id, Room room, List<Book> books) {
//        super();
//        this.id = id;
//        this.available_stock = available_stock;
//        this.book_category = book_category;
//        this.borrowed_number = borrowed_number;
//        this.initial_stock = initial_stock;
//        this.room_id = room_id;
//        this.room = room;
//        this.books = books;
//    }
//
//    // Add getter and setter for id
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//	public Integer getAvailable_stock() {
//		return available_stock;
//	}
//
//	public void setAvailable_stock(Integer available_stock) {
//		this.available_stock = available_stock;
//	}
//
//	public String getBook_category() {
//		return book_category;
//	}
//
//	public void setBook_category(String book_category) {
//		this.book_category = book_category;
//	}
//
//	public Integer getBorrowed_number() {
//		return borrowed_number;
//	}
//
//	public void setBorrowed_number(Integer borrowed_number) {
//		this.borrowed_number = borrowed_number;
//	}
//
//	public Integer getInitial_stock() {
//		return initial_stock;
//	}
//
//	public void setInitial_stock(Integer initial_stock) {
//		this.initial_stock = initial_stock;
//	}
//
//	public UUID getRoom_id() {
//		return room_id;
//	}
//
//	public void setRoom_id(UUID room_id) {
//		this.room_id = room_id;
//	}
//
//	public Room getRoom() {
//		return room;
//	}
//
//	public void setRoom(Room room) {
//		this.room = room;
//	}
//
//	public List<Book> getBooks() {
//		return books;
//	}
//
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}
//
//   
//}