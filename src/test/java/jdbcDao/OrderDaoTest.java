package jdbcDao;

import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void addOrder() throws SQLException {
        Order order = new Order();
        order.setUserId(7L);
        order.setProducts("Xiaomi RedmiBook 15");
        order.setProductsPrice(13.0);
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        order.setOrderTime(myDate.format(myFormatObj));
        orderDao.addOrder(order);
    }

    @Test
    public void getUserOrders() throws SQLException {
        List<Order> userOrders = orderDao.getUserOrders(6L);
        assertEquals(2, userOrders.size());
        assertEquals("2023-06-22 17:33:00", userOrders.get(1).getOrderTime());
        assertEquals("DELL S2422HZ", userOrders.get(0).getProducts());
    }

    @Test
    public void getAllOrders() throws SQLException {
        List<Order> orders = orderDao.getAllOrders();
        assertEquals(9, orders.size());
        assertEquals("2023-06-16 13:59:17", orders.get(2).getOrderTime());
        assertEquals(25.68, orders.get(4).getProductsPrice(), 0);
        assertEquals("Apple MacBook Pro 14\" M2, Apple iPhone 14 Pro", orders.get(7).getProducts());
    }
}