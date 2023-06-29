package hibernateDao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    Dao<User> userDao = new UserDaoImpl();
    @Test
    public void insertUser(){
        User user = new User();
        UserDetails userDetails = new UserDetails();
        userDetails.setLastName("Rudiuk");
        userDetails.setFirstName("Nastya");
        userDetails.setPhoneNumber("380996354821");
        user.setEmail("nastya@gmail.com");
        user.setPassword("fsjfj37");
        user.setUserDetails(userDetails);
        userDao.addNewElement(user);
    }
    @Test
    public void getAllUsers(){
        List<User> users = userDao.getAllElements();
        assertEquals(9, users.size());
        assertNotNull(users.get(7));
        assertEquals("molkjh@gmail.com", users.get(2).getEmail());
        assertEquals("fsjfj37", users.get(8).getPassword());

    }

    @Test
    public void getUserById(){
        User user = userDao.getElementById(5L);
        assertNotNull(user);
        assertEquals("fytrtd43@gmail.com", user.getEmail());
        assertEquals("483ehbikbk", user.getPassword());
    }

    @Test
    public void getUserByIdOutOfTable(){
        User user = userDao.getElementById(11L);
        assertNull(user);
    }

    // update element think about user details

    // delete
}