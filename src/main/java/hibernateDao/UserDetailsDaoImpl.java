package hibernateDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDetailsDaoImpl implements Dao<UserDetails>{
    @Override
    public void addNewElement(UserDetails userDetails) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(userDetails);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<UserDetails> getAllElements() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        List<UserDetails> userDetails = em.createQuery("SELECT u FROM UserDetails u", UserDetails.class).getResultList();
        em.close();
        return userDetails;
    }

    @Override
    public UserDetails getElementById(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        UserDetails userDetails = em.find(UserDetails.class, id);
        return userDetails;
    }

    @Override
    public void updateElement(UserDetails userDetails) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserDetails user = em.find(UserDetails.class, userDetails.getUserDetailsId());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhoneNumber(user.getPhoneNumber());
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void deleteElement(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserDetails user = em.find(UserDetails.class, id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();

    }
}
