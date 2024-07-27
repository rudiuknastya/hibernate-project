package jdbcDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private Logger logger = LogManager.getLogger("jdbc-logger");
    public void addProduct(Long userId, Long productId) {
        String query = "INSERT INTO shopping_cart VALUES (?,?);";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, productId);
            preparedStatement.executeUpdate();
            connection.close();
            logger.info("Product added to cart");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(Long userId, Long productId) {
        String query = "DELETE FROM shopping_cart WHERE user_id=? AND product_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, productId);
            preparedStatement.executeUpdate();
            connection.close();
            logger.info("Product deleted from cart");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Product> getUserProducts(Long userId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT products.product_id, product_name, product_type, price FROM " +
                "(SELECT product_id FROM shopping_cart WHERE user_id = ?) td " +
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
            logger.info("Got user products from cart. List size: "+products.size());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    public void deleteUserProducts(Long userId) {
        String query = "DELETE FROM shopping_cart WHERE user_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","nastya","anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            connection.close();
            logger.info("User products deleted");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
