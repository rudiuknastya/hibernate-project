package hibernateDao;

import jdbcDao.Orders;

import java.util.List;

public interface OrdersDao {
    public void addOrder(Orders order);
    public List<Orders> getUserOrders(long userId);
    public List<Orders> getAllOrders();
}
