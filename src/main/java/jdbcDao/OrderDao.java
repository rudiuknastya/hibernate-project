package jdbcDao;

import java.util.List;

public interface OrdersDao {
    public void addOrder(Orders order);
    public List<Orders> getUserOrders(Long userId);
    public List<Orders> getAllOrders();
}
