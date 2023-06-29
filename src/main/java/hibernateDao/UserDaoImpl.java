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
    public User getElementById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public void updateElement(User user) {
        Long id = user.getUserId();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User foundUser = em.find(User.class, id);
        //System.out.println("user details id "+ user.getUserDetails().getUserDetailsId());
        //UserDetails userDetails = em.find(UserDetails.class, user.getUserDetails().getUserDetailsId());
        //em.merge(user.getUserDetails());
        //Long id = user.getUserDetails().getUserDetailsId();
       // UserDetails userDetails = user.getUserDetails();
        //foundUser.setEmail(user.getEmail());
        foundUser.setPassword(user.getPassword());
        foundUser.getUserDetails().setFirstName(user.getUserDetails().getFirstName());
        foundUser.getUserDetails().setLastName(user.getUserDetails().getLastName());
        foundUser.getUserDetails().setPhoneNumber(user.getUserDetails().getPhoneNumber());
        //em.merge(user.getUserDetails());
        //foundUser.setUserDetails(user.getUserDetails());
        em.merge(foundUser);
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
