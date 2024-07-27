package hibernateDao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //OrderService orderService = new OrderService();
        //orderService.order(14L);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, 5L);
        List<Order> userOrders = user.getOrders();
        em.close();
        System.out.println(userOrders.get(0).getProducts());
    }
}
