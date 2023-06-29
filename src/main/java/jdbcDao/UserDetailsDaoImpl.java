package jdbcDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsDaoImpl implements Dao<UserDetails> {
    @Override
    public void addNewElement(UserDetails userDetails) {
        String query = "INSERT INTO user_details(first_name, last_name, phone_number) VALUES (?,?,?);";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDetails.getFirstName());
            preparedStatement.setString(2, userDetails.getLastName());
            preparedStatement.setString(3, userDetails.getPhoneNumber());
            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDetails> getAllElements() {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userDetailsList;
    }



    @Override
    public UserDetails getElementById(Long id) {
        UserDetails userDetails = new UserDetails();
        String query = "SELECT * FROM user_details WHERE user_details_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                userDetails.setUserDetailsId(result.getLong(1));
                userDetails.setFirstName(result.getString(2));
                userDetails.setLastName(result.getString(3));
                userDetails.setPhoneNumber(result.getString(4));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userDetails;
    }

    @Override
    public void updateElement(UserDetails userDetails) {
        String query = "UPDATE user_details SET first_name=?, last_name=?, phone_number=? WHERE user_details_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDetails.getFirstName());
            preparedStatement.setString(2, userDetails.getLastName());
            preparedStatement.setString(3, userDetails.getPhoneNumber());
            preparedStatement.setLong(4,userDetails.getUserDetailsId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteElement(Long id) {
        String query = "DELETE FROM user_details WHERE user_details_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
