package hibernateDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDetailsDaoImpl implements UserDetailsDao{
    @Override
    public List<UserDetails> getAllUserDetails() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        List<UserDetails> userDetails = em.createQuery("SELECT u FROM UserDetails u", UserDetails.class).getResultList();
        em.close();
        return userDetails;
    }
    @Override
    public UserDetails getUserDetails(Long userId){
        Dao<User> userDao = new UserDaoImpl();
        User user = userDao.getElementById(userId);
        return user.getUserDetails();
    }
}
