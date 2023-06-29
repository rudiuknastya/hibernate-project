package jdbcDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsDaoImpl implements UserDetailsDao {
    private Logger logger = LogManager.getLogger("jdbc-logger");
    @Override
    public List<UserDetails> getAllUserDetails() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        String query = "SELECT * FROM user_details;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                UserDetails userDetails = new UserDetails();
                userDetails.setUserDetailsId(result.getLong(1));
                userDetails.setFirstName(result.getString(2));
                userDetails.setLastName(result.getString(3));
                userDetails.setPhoneNumber(result.getString(4));
                userDetailsList.add(userDetails);
            }
            connection.close();
            logger.info("Got all userDetails. List size: "+userDetailsList.size());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return userDetailsList;
    }


    @Override
    public UserDetails getUserDetails(Long userId) {
        Dao<User> userDao = new UserDaoImpl();
        User user = userDao.getElementById(userId);
        logger.info("Got userDetails. Id: "+user.getUserDetails().getUserDetailsId()+" First name: "+user.getUserDetails().getFirstName()+" Last name: "+user.getUserDetails().getLastName()+" Phone number: "+user.getUserDetails().getPhoneNumber());
        return user.getUserDetails();
    }
}



