package jdbcDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements Dao<User>{
    @Override
    public void addNewElement(User user) {
        String query = "INSERT INTO users(email, pasword, user_details) VALUES (?,?,?);";
        try {
            Dao<UserDetails> userDetailsDao = new UserDetailsDaoImpl();
            userDetailsDao.addNewElement(user.getUserDetails());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getUserDetails().getUserDetailsId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllElements() {

        return null;
    }

    @Override
    public User getElementById(Long id) {

        return null;
    }

    @Override
    public void updateElement(User user) {

    }

    @Override
    public void deleteElement(Long id) {

    }
}
