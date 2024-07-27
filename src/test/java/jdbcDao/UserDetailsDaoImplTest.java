package jdbcDao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDetailsDaoImplTest {
    private UserDetailsDaoImpl userDetailsDao = new UserDetailsDaoImpl();

    @Test
    public void getAllUserDetails() {
        List<UserDetails> userDetailsList = userDetailsDao.getAllUserDetails();
        assertEquals(8, userDetailsList.size());
        assertEquals("Jerry", userDetailsList.get(4).getFirstName());
        assertEquals("Jane", userDetailsList.get(7).getLastName());
        assertEquals("380981653425", userDetailsList.get(0).getPhoneNumber());
    }

    @Test
    public void getUserDetailsById() {
        UserDetails userDetails = userDetailsDao.getUserDetails(5L);
        assertEquals("Linda", userDetails.getFirstName());
        assertEquals("Lawrence", userDetails.getLastName());
        assertEquals("380956437813", userDetails.getPhoneNumber());
    }


}