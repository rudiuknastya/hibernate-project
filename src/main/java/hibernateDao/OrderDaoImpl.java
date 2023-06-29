package hibernateDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao{
    @Override
    public void addOrder(Order order) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userId);
        return user.getOrders();
    }

    @Override
    public List<Order> getAllOrders() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        List<Order> orders = em.createQuery("SELECT o FROM Orders o", Order.class).getResultList();
        em.close();
        return orders;
    }
}
