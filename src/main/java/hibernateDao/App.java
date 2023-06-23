package hibernateDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
    public static void main(String[] args) {
       // Persistence.createEntityManagerFactory()
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        UserDetails user = new UserDetails();
        user.setFirstName("Mike");
        user.setLastName("Jonson");
        user.setPhoneNumber("380957346121");
//        em.getTransaction().begin();
//        UserDetails user1 = em.find(UserDetails.class, 7L);
//        user1.setFirstName(user.getFirstName());
//        //user1 = user;
//        //em.persist(user);
//        em.getTransaction().commit();
        //UserDetails user1 = em.find(UserDetails.class, 4L);
        List<UserDetails> userDetails = em.createQuery("SELECT u FROM UserDetails u", UserDetails.class).getResultList();
        em.close();
        //UserDetails u = (UserDetails)userDetails.get(0);
       System.out.println(userDetails.get(0).getFirstName());
    }
}
