package jdbcDao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    Dao<User> userDao = new UserDaoImpl();
    @Test
    public void insertUser(){
        User user = new User();
        UserDetails userDetails = new UserDetails();
        userDetails.setLastName("Sasha");
        userDetails.setFirstName("Sun");
        userDetails.setPhoneNumber("380996342117");
        user.setEmail("whyouy@gmail.com");
        user.setPassword("snauvc51");
        user.setUserDetails(userDetails);
        userDao.addNewElement(user);
    }

    @Test
    public void getAllUsers(){
        List<User> allUsers = userDao.getAllElements();
        assertEquals(9, allUsers.size());
        assertEquals("khjgjgof@gmail.com", allUsers.get(2).getEmail());
        assertEquals("madvgb", allUsers.get(6).getPassword());
        assertEquals(33L, allUsers.get(8).getUserDetails().getUserDetailsId(),0);
    }
    @Test
    public void getUserById(){
        User user = userDao.getElementById(11L);
        assertEquals("whyouy@gmail.com", user.getEmail());
        assertEquals("snauvc51", user.getPassword());
        assertEquals(33L, user.getUserDetails().getUserDetailsId(),0);
    }
    @Test
    public void updateUser(){
        User user = new User();
        UserDetails userDetails = new UserDetails();
        userDetails.setUserDetailsId(12L);
        userDetails.setFirstName("Harry");
        userDetails.setLastName("Potter");
        userDetails.setPhoneNumber("380996354821");
        user.setUserDetails(userDetails);
        user.setEmail("hiiiii@gmail.com");
        user.setPassword("vacxder41");
        user.setUserId(9L);
        userDao.updateElement(user);
    }
    @Test
    public void deleteUser(){
        userDao.deleteElement(11L);
    }
}