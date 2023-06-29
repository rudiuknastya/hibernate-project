package jdbcDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements Dao<Product>{
    private Logger logger = LogManager.getLogger("jdbc-logger");
    @Override
    public void addNewElement(Product product) {
        String query = "INSERT INTO products(product_name, product_type, price) VALUES (?,?,?);";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductType());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();
            connection.close();
            logger.info("Product added");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAllElements() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Product product = new Product();
                product.setProductId(result.getLong(1));
                product.setProductName(result.getString(2));
                product.setProductType(result.getString(3));
                product.setPrice(result.getDouble(4));
                products.add(product);
            }
            connection.close();
            logger.info("Got all products. List size: "+products.size());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product getElementById(Long id) {
        Product product = new Product();
        String query = "SELECT * FROM products WHERE product_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                product.setProductId(result.getLong(1));
                product.setProductName(result.getString(2));
                product.setProductType(result.getString(3));
                product.setPrice(result.getDouble(4));
            }
            connection.close();
            logger.info("Got product. Id: "+product.getProductId()+" Name: "+product.getProductName()+" Type: "+product.getProductType()+" Price: "+product.getPrice());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void updateElement(Product product) {
        String query = "UPDATE products SET product_name=?, product_type=?, price=? WHERE product_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductType());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setLong(4,product.getProductId());
            preparedStatement.executeUpdate();
            connection.close();
            logger.info("Product updated");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteElement(Long id) {
        String query = "DELETE FROM products WHERE product_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "nastya", "anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            connection.close();
            logger.info("Product deleted");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
