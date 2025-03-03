package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends Person {
    @Column(name = "password")
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    
    @Column(name = "user_name")
    private String user_name;
    
    @ManyToOne
    @JoinColumn(name = "village_id")
    private Location village;
    
    public User() {
		super();
	}

	public User(String password, Role role, String user_name, Location village) {
		super();
		this.password = password;
		this.role = role;
		this.user_name = user_name;
		this.village = village;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Location getVillage() {
		return village;
	}

	public void setVillage(Location village) {
		this.village = village;
	}

}

