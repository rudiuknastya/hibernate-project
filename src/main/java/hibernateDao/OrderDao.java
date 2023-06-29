package hibernateDao;
import java.util.List;

public interface OrdersDao {
    public void addOrder(Order order);
    public List<Order> getUserOrders(Long userId);
    public List<Order> getAllOrders();
}
