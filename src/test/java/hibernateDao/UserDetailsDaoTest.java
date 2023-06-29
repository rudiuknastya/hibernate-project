package hibernateDao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDetailsDaoTest {
    UserDetailsDao userDetailsDao = new UserDetailsDaoImpl();
    @Test
    public void getAllUserDetails(){
       List<UserDetails> userDetailsList = userDetailsDao.getAllUserDetails();
       assertEquals(9, userDetailsList.size());
       assertEquals("Lina", userDetailsList.get(0).getFirstName());
       assertEquals("380996354821", userDetailsList.get(8).getPhoneNumber());
       assertEquals("Lawrence", userDetailsList.get(4).getLastName());
    }
    @Test
    public void getUserDetails(){
        UserDetails userDetails = userDetailsDao.getUserDetails(7L);
        assertEquals("Sofie", userDetails.getFirstName());
        assertEquals("Brown", userDetails.getLastName());
        assertEquals("380775243624", userDetails.getPhoneNumber());
    }

}