package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "location")
public class Location implements Serializable {
    
    @Id
    @Column(name = "location_id")
    private UUID locationId=UUID.randomUUID();
    
    @Column(name = "location_name")
    private String locationName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "location_type")
    private Location_type locationType;
    
    @Column(name = "location_code")
    private String locationCode;
    
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Location parentId;
    
//    public Location() {
//		super();
//	}

//	public Location(UUID locationId, String locationName, Location_type locationType, String locationCode, UUID parentId) {
//		super();
//		this.locationId = locationId;
//		this.locationName = locationName;
//		this.locationType = locationType;
//		this.locationCode = locationCode;
//		this.parentId = parentId;
//	}

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Location_type getLocationType() {
		return locationType;
	}

	public void setLocationType(Location_type locationType) {
		this.locationType = locationType;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Location getParentId() {
		return parentId;
	}

	public void setParentId(Location parentId) {
		this.parentId = parentId;
	}

	// Default constructor
    public Location() {
    }
    
   
}