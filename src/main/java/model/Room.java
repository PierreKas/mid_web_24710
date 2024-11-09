package model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Room {

    @Column(name = "room_code")
    private String room_code;
    @Id
    @Column(name = "room_id")
     private UUID room_id=UUID.randomUUID();
	    
//     @OneToMany(mappedBy = "room")
//     private List<Shelf> shelves;
	    
	public Room() {
		super();
	}

	public String getRoom_code() {
		return room_code;
	}

	public void setRoom_code(String room_code) {
		this.room_code = room_code;
	}

	public UUID getRoom_id() {
		return room_id;
	}

	public void setRoom_id(UUID room_id) {
		this.room_id = room_id;
	}

//	public List<Shelf> getShelves() {
//		return shelves;
//	}
//
//	public void setShelves(List<Shelf> shelves) {
//		this.shelves = shelves;
//	}

	public Room(String room_code, UUID room_id
			//List<Shelf> shelves
			) {
		super();
		this.room_code = room_code;
		this.room_id = room_id;
		//this.shelves = shelves;
	}

	
}
