package model;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    private UUID book_id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Book_status")
    private Book_status status;
    
    @Column(name = "edition")
    private Integer edition;
    
    @Column(name = "ISBNCode")
    private String ISBNCode;
    
    @Column(name = "publication_year")
    private Date publication_year;
    
    @Column(name = "publisher_name")
    private String publisher_name;
    
    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;
    
    @Column(name = "title")
    private String title;
    
    public Book(UUID book_id, Book_status status, Integer edition, String iSBNCode, Date publication_year,
			String publisher_name, Shelf shelf, String title, List<Borrower> borrowers) {
		super();
		this.book_id = book_id;
		this.status = status;
		this.edition = edition;
		ISBNCode = iSBNCode;
		this.publication_year = publication_year;
		this.publisher_name = publisher_name;
		this.shelf = shelf;
		this.title = title;
		this.borrowers = borrowers;
	}

	public UUID getBook_id() {
		return book_id;
	}

	public void setBook_id(UUID book_id) {
		this.book_id = book_id;
	}

	public Book_status getStatus() {
		return status;
	}

	public void setStatus(Book_status status) {
		this.status = status;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public String getISBNCode() {
		return ISBNCode;
	}

	public void setISBNCode(String iSBNCode) {
		ISBNCode = iSBNCode;
	}

	public Date getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(Date publication_year) {
		this.publication_year = publication_year;
	}

	public String getPublisher_name() {
		return publisher_name;
	}

	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Borrower> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

	@OneToMany(mappedBy = "book_id")
    private List<Borrower> borrowers;
}
