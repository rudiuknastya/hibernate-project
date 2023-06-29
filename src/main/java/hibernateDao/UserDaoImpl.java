package hibernateDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDaoImpl implements Dao<User>{
    @Override
    public void addNewElement(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<User> getAllElements() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return users;
    }

    @Override
    public User getElementById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    @Override
    public void updateElement(User user) {
        Long id = user.getUserId();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteElement(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }
}
