package jdbcDao;

import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersDaoTest {
    @Test
    public void addOrder() throws SQLException {
       OrdersDao ordersDao = new OrdersDaoImpl();
       Orders order = new Orders();
       order.setUserId(6);
       order.setProducts("Samsung Galaxy A54, HP V24 FHD");
       order.setProductsPrice(6.88);
       LocalDateTime myDate = LocalDateTime.now();
       DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       order.setOrderTime(myDate.format(myFormatObj));
       assertEquals("Added", ordersDao.addOrder(order));
    }

    @Test
    public void getUserOrders() throws SQLException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        List<Orders> userOrders = ordersDao.getUserOrders(6L);
        assertEquals(2, userOrders.size());
    }
    @Test
    public void getUserOrderTime() throws SQLException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        List<Orders> userOrders = ordersDao.getUserOrders(5L);
        assertEquals("2023-06-22 11:18:35", userOrders.get(1).getOrderTime());
    }
    @Test
    public void getUserOrderProducts() throws SQLException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        List<Orders> userOrders = ordersDao.getUserOrders(4L);
        assertEquals("Samsung Galaxy Tab A8, Apple iPhone 14 Pro, Xiaomi 13 Lite", userOrders.get(0).getProducts());
    }
    @Test
    public void getAllOrders() throws SQLException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        List<Orders> orders = ordersDao.getAllOrders();
        assertEquals(7, orders.size());
    }
    @Test
    public void getAllOrdersTime() throws SQLException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        List<Orders> orders = ordersDao.getAllOrders();
        assertEquals("2023-06-16 13:59:17", orders.get(2).getOrderTime());
    }

    @Test
    public void getAllOrdersProductPrice() throws SQLException {
        OrdersDao ordersDao = new OrdersDaoImpl();
        List<Orders> orders = ordersDao.getAllOrders();
        assertEquals(25.68, orders.get(4).getProductsPrice(),0);
    }
}