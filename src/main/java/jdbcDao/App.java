package jdbcDao;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        orderService.order(1L);
    }
}
