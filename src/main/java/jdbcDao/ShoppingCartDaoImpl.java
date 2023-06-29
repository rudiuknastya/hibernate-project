package jdbcDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

    public String addProduct(Long userId, Long productId) {
        String query = "INSERT INTO shopping_cart VALUES (?,?);";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, productId);
            preparedStatement.executeUpdate();
            connection.close();
            return "Added";
        } catch (SQLException e) {
            return "notAdded";
            //throw new RuntimeException(e);
        }
        //return "notAdded";
    }

    public String deleteProduct(Long userId, Long productId) {
        String query = "DELETE FROM shopping_cart WHERE user_id=? AND product_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, productId);
            preparedStatement.executeUpdate();
            connection.close();
            return "Deleted";
        } catch (SQLException e) {
            return "Not Deleted";
            //throw new RuntimeException(e);
        }
    }

    public List<Product> getUserProducts(Long userId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT products.product_id, product_name, product_type, price FROM " +
                "(SELECT product_id FROM shopping_cart WHERE user_id = ?) " +
                "INNER JOIN products ON td.product_id = products.product_id;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product();
                product.setProductId(result.getLong(1));
                product.setProductName(result.getString(2));
                product.setProductType(result.getString(3));
                product.setPrice(result.getDouble(4));
                products.add(product);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public String deleteUserProducts(Long userId) {
        String query = "DELETE FROM shopping_cart WHERE user_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","nastya","anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            connection.close();
            return "Deleted";
        } catch (SQLException e) {
            return "Not deleted";
            // throw new RuntimeException(e);
        }
    }

}
