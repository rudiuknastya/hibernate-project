package hibernateDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
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
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        List<Order> userOrders = user.getOrders();
        userOrders.get(0);
        em.getTransaction().commit();
        em.close();
        return userOrders;
    }

    @Override
    public List<Order> getAllOrders() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Order> orders = em.createQuery("SELECT o FROM Order o left join fetch o.user", Order.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return orders;
    }
}
