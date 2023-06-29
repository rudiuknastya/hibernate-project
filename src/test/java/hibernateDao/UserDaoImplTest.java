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
        userDetails.setLastName("Jack");
        userDetails.setFirstName("Harrlow");
        userDetails.setPhoneNumber("380953976133");
        user.setEmail("jack@gmail.com");
        user.setPassword("njags5");
        user.setUserDetails(userDetails);
        userDao.addNewElement(user);
    }
    @Test
    public void getAllUsers(){
        List<User> users = userDao.getAllElements();
        assertEquals(9, users.size());
        assertNotNull(users.get(7));
        assertEquals("molkjh@gmail.com", users.get(1).getEmail());
        assertEquals("fsjfj37", users.get(8).getPassword());

    }

    @Test
    public void getUserById(){
        User user = userDao.getElementById(8L);
        assertNotNull(user);
        assertEquals("cngdg@gmail.com", user.getEmail());
        assertEquals("madvgb", user.getPassword());
        assertEquals("Anna", user.getUserDetails().getFirstName());
    }

    @Test
    public void getUserByIdOutOfTable(){
        User user = userDao.getElementById(11L);
        assertNull(user);
    }
    @Test
    public void updateUser(){
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("Anna");
        userDetails.setLastName("Croft");
        userDetails.setPhoneNumber("380991542388");
        User user = new User();
        user.setUserId(8L);
        user.setPassword("madvgb");
        user.setUserDetails(userDetails);
        userDao.updateElement(user);
    }

    @Test
    public void deleteUser(){
        userDao.deleteElement(12L);
    }
}