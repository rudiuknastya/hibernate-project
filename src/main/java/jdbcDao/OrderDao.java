package jdbcDao;

import java.util.List;

public interface OrderDao {
    public void addOrder(Order order);
    public List<Order> getUserOrders(Long userId);
    public List<Order> getAllOrders();
}
