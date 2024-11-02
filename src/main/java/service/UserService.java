package service;

//import dao.UserDao;
//import model.User;
//import util.PasswordUtil;
//
//public class UserService {
//    private UserDao userDao;
//
//    public UserService() {
//        userDao = new UserDao();
//    }
//
//    public boolean registerUser(User user) {
//        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
//        return userDao.save(user);
//    }
//
//    public String authenticate(String username, String password) {
//        User user = userDao.findByUsername(username);
//        return (user != null && PasswordUtil.checkPassword(password, user.getPassword())) ? user.getRole() : null;
//    }
//
//    public String getUserLocationByPhone(String phoneNumber) {
//        return userDao.findLocationByPhone(phoneNumber);
//    }
//}


import model.User;
//import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("auca_library_db");

//    public String encryptPassword(String plainPassword) {
//        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
//    }

    public void saveUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }
}


