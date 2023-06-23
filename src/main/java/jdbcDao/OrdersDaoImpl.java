package jdbcDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao{
    private Connection connection; // = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","nastya","anastasiia");
    public OrdersDaoImpl() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","nastya","anastasiia");
    }
    public String addOrder(Orders order){
        String query = "INSERT INTO orders(user_id, products, products_price, order_time) VALUES (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,order.getUserId());
            preparedStatement.setString(2, order.getProducts());
            preparedStatement.setDouble(3,order.getProductsPrice());
            preparedStatement.setString(4,order.getOrderTime());
            preparedStatement.executeUpdate();
            connection.close();
            return "Added";
        } catch (SQLException e) {
            return "Not Added";
            //throw new RuntimeException(e);
        }
    }
    public List<Orders> getUserOrders(long userId){
        List<Orders> userOrders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id=?;";
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userOrders;
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM orders;";
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;

    }
}
