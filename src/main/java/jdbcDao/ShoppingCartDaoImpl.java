package jdbcDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoImpl implements ShoppingCartDao{
    private Connection connection;
    public ShoppingCartDaoImpl() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","nastya","anastasiia");
    }

    public String addProduct(ShoppingCart cart) {
        String query = "INSERT INTO shopping_cart VALUES (?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,cart.getUserId());
            preparedStatement.setLong(2,cart.getProductId());
            preparedStatement.executeUpdate();
            connection.close();
            return "Added";
        } catch (SQLException e) {
            return "notAdded";
            //throw new RuntimeException(e);
        }
        //return "notAdded";
    }
    public String deleteProduct(ShoppingCart cart) {
        String query = "DELETE FROM shopping_cart WHERE user_id=? AND product_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,cart.getUserId());
            preparedStatement.setLong(2,cart.getProductId());
            preparedStatement.executeUpdate();
            connection.close();
            return "Deleted";
        } catch (SQLException e) {
            return "Not Deleted";
            //throw new RuntimeException(e);
        }
    }
    public List<Long> getUserProducts(Long userId){
        List<Long> products = new ArrayList<>();
        String query = "SELECT product_id FROM shopping_cart WHERE user_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,userId);
           ResultSet result = preparedStatement.executeQuery();
           //List<Long> products = new ArrayList<>();
           while(result.next()){
               products.add(result.getLong("product_id"));
           }
           connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public String deleteUserProducts(Long userId){
        String query = "DELETE FROM shopping_cart WHERE user_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,userId);
            preparedStatement.executeUpdate();
            connection.close();
            return "Deleted";
        } catch (SQLException e) {
            return "Not deleted";
           // throw new RuntimeException(e);
        }
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
