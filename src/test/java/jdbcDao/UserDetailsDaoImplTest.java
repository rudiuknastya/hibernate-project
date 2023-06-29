package jdbcDao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDetailsDaoImplTest {
    private UserDetailsDaoImpl userDetailsDao = new UserDetailsDaoImpl();
    @Test
    public void addUserDetails(){
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("Ann");
        userDetails.setLastName("Sandly");
        userDetails.setPhoneNumber("380664572248");
        userDetailsDao.addNewElement(userDetails);
    }
    @Test
    public void getAllUserDetailsCompareSize(){
        List<UserDetails> userDetailsList = userDetailsDao.getAllElements();
        assertEquals(7, userDetailsList.size());
    }
    @Test
    public void getAllUserDetailsCompareFirstName(){
        List<UserDetails> userDetailsList = userDetailsDao.getAllElements();
        assertEquals("Jerry", userDetailsList.get(5).getFirstName());
    }
    @Test
    public void getUserDetailsByIdCompareLastName(){
        UserDetails userDetails = userDetailsDao.getElementById(2L);
        assertEquals("Thompson", userDetails.getLastName());
    }
    @Test
    public void updateUserDetails(){
        UserDetails userDetails = new UserDetails();
        userDetails.setUserDetailsId(9L);
        userDetails.setFirstName("Ann");
        userDetails.setLastName("Sandler");
        userDetails.setPhoneNumber("380664572248");
        userDetailsDao.updateElement(userDetails);
    }
    @Test
    public void deleteUserDetails(){
        userDetailsDao.deleteElement(9L);
    }

}