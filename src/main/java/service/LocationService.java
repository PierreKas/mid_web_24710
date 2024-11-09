package service;

import dao.LocationDAO;
import model.Location;
import model.Location_type;

import java.util.List;
import java.util.UUID;

public class LocationService {
    private LocationDAO locationDAO;
    
    public LocationService() {
        this.locationDAO = new LocationDAO();
    }
    
    public void createLocation(Location location) throws Exception {
        // Add business logic validation
        if (location.getLocationName() == null || location.getLocationName().trim().isEmpty()) {
            throw new Exception("Location name cannot be empty");
        }
        if (location.getLocationCode() == null || location.getLocationCode().trim().isEmpty()) {
            throw new Exception("Location code cannot be empty");
        }
        
        // Check if location code already exists
        Location existingLocation = locationDAO.getLocationByCode(location.getLocationCode());
        if (existingLocation != null) {
            throw new Exception("Location code already exists");
        }
        
        // Validate parent-child relationship
//        if (location.getParentId() != null) {
//            Location parent = locationDAO.getLocationById(location.getParentId());
//            if (parent == null) {
//                throw new Exception("Parent location does not exist");
//            }
//            validateLocationHierarchy(location.getLocationType(), parent.getLocationType());
//        }
        
        locationDAO.saveLocation(location);
    }
    
    public void updateLocation(Location location) throws Exception {
        // Similar validations as create
        if (location.getLocationName() == null || location.getLocationName().trim().isEmpty()) {
            throw new Exception("Location name cannot be empty");
        }
        
        // Check if location exists
        Location existingLocation = locationDAO.getLocationById(location.getLocationId());
        if (existingLocation == null) {
            throw new Exception("Location not found");
        }
        
        // Validate parent-child relationship if parent is changed
//        if (location.getParentId() != null && 
//            !location.getParentId().equals(existingLocation.getParentId())) {
//            Location parent = locationDAO.getLocationById(location.getParentId());
//            if (parent == null) {
//                throw new Exception("Parent location does not exist");
//            }
//            validateLocationHierarchy(location.getLocationType(), parent.getLocationType());
//        }
        
        locationDAO.saveLocation(location);
    }
    
    public void deleteLocation(UUID locationId) throws Exception {
        // Check if location has children
        List<Location> children = locationDAO.getChildLocations(locationId);
        if (!children.isEmpty()) {
            throw new Exception("Cannot delete location with child locations");
        }
        
        Location location = locationDAO.getLocationById(locationId);
        if (location != null) {
            locationDAO.deleteLocation(location);
        } else {
            throw new Exception("Location not found");
        }
    }
    
    public Location getLocationById(UUID id) {
        return locationDAO.getLocationById(id);
    }
    
    public List<Location> getAllLocations() {
        return locationDAO.getAllLocations();
    }
    
    public List<Location> getLocationsByType(Location_type type) {
        return locationDAO.getLocationsByType(type);
    }
    
    private void validateLocationHierarchy(Location_type childType, Location_type parentType) throws Exception {
        // Define valid parent-child relationships
        switch (childType) {
            case DISTRICT:
                if (parentType != Location_type.PROVINCE) {
                    throw new Exception("District must have Province as parent");
                }
                break;
            case SECTOR:
                if (parentType != Location_type.DISTRICT) {
                    throw new Exception("Sector must have District as parent");
                }
                break;
            case CELL:
                if (parentType != Location_type.SECTOR) {
                    throw new Exception("Cell must have Sector as parent");
                }
                break;
            case VILLAGE:
                if (parentType != Location_type.CELL) {
                    throw new Exception("Village must have Cell as parent");
                }
                break;
            case PROVINCE:
                // Allow Province to have a null parent
                break;
            default:
                throw new Exception("Invalid location type");
        }
    }
    
    public List<Location> getPossibleParents(Location_type childType) {
        Location_type parentType = null;
        switch (childType) {
            case DISTRICT:
                parentType = Location_type.PROVINCE;
                break;
            case SECTOR:
                parentType = Location_type.DISTRICT;
                break;
            case CELL:
                parentType = Location_type.SECTOR;
                break;
            case VILLAGE:
                parentType = Location_type.CELL;
                break;
        }
        return parentType != null ? locationDAO.getLocationsByType(parentType) : null;
    }
}