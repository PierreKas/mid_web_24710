package service;

import dao.UserDAO;
import model.User;
import model.Role;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Base64;

public class UserService {
    private UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
    }
    
    public void createUser(User user) throws Exception {
        // Validate required fields
        if (user.getFirst_name() == null || user.getFirst_name().trim().isEmpty()) {
            throw new Exception("First name cannot be empty");
        }
        if (user.getLast_name() == null || user.getLast_name().trim().isEmpty()) {
            throw new Exception("Last name cannot be empty");
        }
        if (user.getUser_name() == null || user.getUser_name().trim().isEmpty()) {
            throw new Exception("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new Exception("Password cannot be empty");
        }
        
        // Check if username already exists
        User existingUser = userDAO.getUserByUsername(user.getUser_name());
        if (existingUser != null) {
            throw new Exception("Username already exists");
        }
        
        // Hash the password before saving
        user.setPassword(hashPassword(user.getPassword()));
        
        userDAO.saveUser(user);
    }
    
    
    
    public void updateUser(User user) throws Exception {
        // Validate required fields
        if (user.getFirst_name() == null || user.getFirst_name().trim().isEmpty()) {
            throw new Exception("First name cannot be empty");
        }
        if (user.getLast_name() == null || user.getLast_name().trim().isEmpty()) {
            throw new Exception("Last name cannot be empty");
        }
        if (user.getUser_name() == null || user.getUser_name().trim().isEmpty()) {
            throw new Exception("Username cannot be empty");
        }
        
        // Check if user exists
        User existingUser = userDAO.getUserById(user.getPerson_id());
        if (existingUser == null) {
            throw new Exception("User not found");
        }
        
        // Check username uniqueness (excluding current user)
        User userWithSameUsername = userDAO.getUserByUsername(user.getUser_name());
        if (userWithSameUsername != null && !userWithSameUsername.getPerson_id().equals(user.getPerson_id())) {
            throw new Exception("Username already exists");
        }
        
        // Only update password if a new one is provided
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            user.setPassword(hashPassword(user.getPassword()));
        } else {
            // Keep existing password
            user.setPassword(existingUser.getPassword());
        }
        
        userDAO.saveUser(user);
    }
    
    public void deleteUser(String userId) throws Exception {
        User user = userDAO.getUserById(userId);
        if (user != null) {
            userDAO.deleteUser(user);
        } else {
            throw new Exception("User not found");
        }
    }
    
    public User getUserById(String id) {
        return userDAO.getUserById(id);
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
    public List<User> getUsersByRole(Role role) {
        return userDAO.getUsersByRole(role);
    }
    
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
}