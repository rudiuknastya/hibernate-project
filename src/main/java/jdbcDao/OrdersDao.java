package jdbcDao;

import java.util.List;

public interface OrdersDao {
    public String addOrder(Orders order);
    public List<Orders> getUserOrders(long userId);
    public List<Orders> getAllOrders();
}
