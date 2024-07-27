package jdbcDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Dao<User>{
    private Logger logger = LogManager.getLogger("jdbc-logger");
    @Override
    public void addNewElement(User user) {
        String queryInsertUserDetails = "INSERT INTO user_details(first_name, last_name, phone_number) VALUES (?,?,?);";
        String queryUserDetailsId= "SELECT user_details_id FROM user_details WHERE phone_number=?;";
        String queryInsertUser = "INSERT INTO users(email, pasword, user_details) VALUES (?,?,?);";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(queryInsertUserDetails);
            preparedStatement.setString(1, user.getUserDetails().getFirstName());
            preparedStatement.setString(2, user.getUserDetails().getLastName());
            preparedStatement.setString(3, user.getUserDetails().getPhoneNumber());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = connection.prepareStatement(queryUserDetailsId);
            preparedStatement1.setString(1, user.getUserDetails().getPhoneNumber());
            ResultSet result = preparedStatement1.executeQuery();
            while (result.next()){
                user.getUserDetails().setUserDetailsId(result.getLong(1));
            }
            PreparedStatement preparedStatement3 = connection.prepareStatement(queryInsertUser);
            preparedStatement3.setString(1, user.getEmail());
            preparedStatement3.setString(2, user.getPassword());
            preparedStatement3.setLong(3, user.getUserDetails().getUserDetailsId());
            preparedStatement3.executeUpdate();
            connection.close();
            logger.info("User and UserDetails added");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllElements() {
        List<User> users = new ArrayList<>();
        String query = "SELECT user_id, email, pasword, user_details_id, first_name, last_name, phone_number FROM users " +
                "INNER JOIN user_details ON users.user_details = user_details.user_details_id;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                User user = new User();
                UserDetails userDetails = new UserDetails();
                user.setUserId(result.getLong(1));
                user.setEmail(result.getString(2));
                user.setPassword(result.getString(3));
                userDetails.setUserDetailsId(result.getLong(4));
                userDetails.setFirstName(result.getString(5));
                userDetails.setLastName(result.getString(6));
                userDetails.setPhoneNumber(result.getString(7));
                user.setUserDetails(userDetails);
                users.add(user);
            }
            logger.info("Got all users. List size: "+users.size());
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getElementById(Long id) {
        User user = new User();
        String query = "SELECT user_id, email, pasword, user_details_id, first_name, last_name, phone_number FROM users " +
                "INNER JOIN user_details ON users.user_details = user_details.user_details_id WHERE users.user_id = ?";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                user.setUserId(result.getLong(1));
                user.setEmail(result.getString(2));
                user.setPassword(result.getString(3));
                UserDetails userDetails = new UserDetails();
                userDetails.setUserDetailsId(result.getLong(4));
                userDetails.setFirstName(result.getString(5));
                userDetails.setLastName(result.getString(6));
                userDetails.setPhoneNumber(result.getString(7));
                user.setUserDetails(userDetails);

            }
            connection.close();
            logger.info("Got user. Id: "+user.getUserId()+" E-mail: "+user.getEmail()+" Password: "+user.getPassword()+" UserDetails id: "+user.getUserDetails().getUserDetailsId());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void updateElement(User user) {
        String query = "UPDATE users, user_details " +
                "SET email = ?, " +
                "pasword = ?, " +
                "first_name = ?, " +
                "last_name = ?, " +
                "phone_number = ? " +
                "WHERE users.user_details = user_details.user_details_id AND user_id = ?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserDetails().getFirstName());
            preparedStatement.setString(4, user.getUserDetails().getLastName());
            preparedStatement.setString(5, user.getUserDetails().getPhoneNumber());
            preparedStatement.setLong(6,user.getUserId());
            preparedStatement.executeUpdate();
            connection.close();
            logger.info("User and UserDetails updated");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteElement(Long id) {
        String selectUserDetails = "SELECT user_details FROM users WHERE user_id = ?;";
        String query = "delete from user_details where user_details_id = ?;";
        String deleteOrdersQuery = "DELETE FROM orders where orders.user_id = ?;";
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
        shoppingCartDao.deleteUserProducts(id);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(deleteOrdersQuery);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = connection.prepareStatement(selectUserDetails);
            preparedStatement1.setLong(1, id);
            ResultSet result = preparedStatement1.executeQuery();
            Long udId = null;
            while (result.next()) {
                udId = result.getLong(1);
            }

            PreparedStatement preparedStatement2 = connection.prepareStatement(query);
            preparedStatement2.setLong(1,udId);
            preparedStatement2.executeUpdate();
            connection.close();
            logger.info("User and UserDetails deleted");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
