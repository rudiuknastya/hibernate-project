package jdbcDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao{

    private Logger logger = LogManager.getLogger("jdbc-logger");
    public void addOrder(Orders order){
        String query = "INSERT INTO orders(user_id, products, products_price, order_time) VALUES (?,?,?,?);";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","nastya","anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,order.getUserId());
            preparedStatement.setString(2, order.getProducts());
            preparedStatement.setDouble(3,order.getProductsPrice());
            preparedStatement.setString(4,order.getOrderTime());
            preparedStatement.executeUpdate();
            connection.close();
            logger.info("Order added");
        } catch (SQLException e) {
            logger.error(e.getMessage());
           throw new RuntimeException(e);
        }
    }
    public List<Orders> getUserOrders(Long userId){
        List<Orders> userOrders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id=?;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","nastya","anastasiia");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,userId);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                Orders order = new Orders();
                order.setOrderId(result.getLong(1));
                order.setUserId(result.getLong(2));
                order.setProducts(result.getString(3));
                order.setProductsPrice(result.getDouble(4));
                order.setOrderTime(result.getString(5));
                userOrders.add(order);

            }
            connection.close();
            logger.info("Got user orders. List size: "+userOrders.size());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return userOrders;
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM orders;";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","nastya","anastasiia");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Orders order = new Orders();
                order.setOrderId(result.getLong(1));
                order.setUserId(result.getLong(2));
                order.setProducts(result.getString(3));
                order.setProductsPrice(result.getDouble(4));
                order.setOrderTime(result.getString(5));
                orders.add(order);
            }
            connection.close();
            logger.info("Got user orders. List size: "+orders.size());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return orders;

    }
}
