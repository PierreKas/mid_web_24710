//package model;
//
//import java.util.List;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "membership_type")
//public class membership_type {
//    @Column(name = "max_books")
//    private Integer max_books;
//    
//    @Column(name = "membership_name")
//    private String membership_name;
//    
//    public membership_type(Integer max_books, String membership_name, UUID membership_type_id, Integer price,
//			List<Membership> memberships) {
//		super();
//		this.max_books = max_books;
//		this.membership_name = membership_name;
//		this.membership_type_id = membership_type_id;
//		this.price = price;
//		this.memberships = memberships;
//	}
//
//	public Integer getMax_books() {
//		return max_books;
//	}
//
//	public void setMax_books(Integer max_books) {
//		this.max_books = max_books;
//	}
//
//	public String getMembership_name() {
//		return membership_name;
//	}
//
//	public void setMembership_name(String membership_name) {
//		this.membership_name = membership_name;
//	}
//
//	public UUID getMembership_type_id() {
//		return membership_type_id;
//	}
//
//	public void setMembership_type_id(UUID membership_type_id) {
//		this.membership_type_id = membership_type_id;
//	}
//
//	public Integer getPrice() {
//		return price;
//	}
//
//	public void setPrice(Integer price) {
//		this.price = price;
//	}
//
//	public List<Membership> getMemberships() {
//		return memberships;
//	}
//
//	public void setMemberships(List<Membership> memberships) {
//		this.memberships = memberships;
//	}
//
//	@Id
//    @Column(name = "membership_type_id")
//    private UUID membership_type_id=UUID.randomUUID();
//    
//    @Column(name = "price")
//    private Integer price;
//    
//    @OneToMany(mappedBy = "membershipType")
//    private List<Membership> memberships;
//}
