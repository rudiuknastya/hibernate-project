package hibernateDao;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    OrderDao ordersDao = new OrderDaoImpl();

    @Test
    public void addOrder(){
        Dao<User> userDao = new UserDaoImpl();
        User user = userDao.getElementById(1L);
        Order order = new Order();
        order.setProducts("Apple MacBook Pro 14\" M2, Apple iPhone 14 Pro");
        order.setProductsPrice(36.45);
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        order.setOrderTime(myDate.format(myFormatObj));
        order.setUser(user);
        ordersDao.addOrder(order);
        assertEquals(2, user.getOrders().size());
    }

    @Test
    public void getUserOrders(){
        List<Order> orders = ordersDao.getUserOrders(5L);
        assertEquals(2, orders.size());
        assertEquals("Xiaomi Mi TV Q1E 55", orders.get(1).getProducts());
        assertEquals(19.0, orders.get(0).getProductsPrice(), 0);
        assertEquals("2023-06-22 11:18:35", orders.get(1).getOrderTime());
    }

    @Test
    public void getAllOrders(){
        List<Order> orders = ordersDao.getAllOrders();
        assertEquals(8, orders.size());
        assertEquals("Xiaomi Mi TV Q1E 55, Lenovo Tab M10 Plus (3 Gen)", orders.get(0).getProducts());
        assertEquals(5.49, orders.get(3).getProductsPrice(), 0);
        assertEquals("2023-06-26 22:43:41", orders.get(7).getOrderTime());
    }
}