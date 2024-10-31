package model;

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
@Table(name = "Location")
public class Location {
    @Column(name = "location_code")
    private String location_code;
    
    @Id
    @Column(name = "location_id")
    private UUID location_id;
    
    @Column(name = "location_name")
    private String location_name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Location_Type")
    private Location_type type;
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Location parent;
    
    @OneToMany(mappedBy = "parent")
    private List<Location> children;
    
    public Location(String location_code, UUID location_id, String location_name, Location_type type, Location parent,
			List<Location> children, List<User> users) {
		super();
		this.location_code = location_code;
		this.location_id = location_id;
		this.location_name = location_name;
		this.type = type;
		this.parent = parent;
		this.children = children;
		this.users = users;
	}

	public String getLocation_code() {
		return location_code;
	}

	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}

	public UUID getLocation_id() {
		return location_id;
	}

	public void setLocation_id(UUID location_id) {
		this.location_id = location_id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public Location_type getType() {
		return type;
	}

	public void setType(Location_type type) {
		this.type = type;
	}

	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	public List<Location> getChildren() {
		return children;
	}

	public void setChildren(List<Location> children) {
		this.children = children;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "village_id")
    private List<User> users;
}
