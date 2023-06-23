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
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.close();
        return users;
    }

    @Override
    public User getElementById(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public void updateElement(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User foundUser = em.find(User.class, user.getUserId());
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(user.getPassword());
        foundUser.setUserDetails(user.getUserDetails());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteElement(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }
}
