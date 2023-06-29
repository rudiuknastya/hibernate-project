package hibernateDao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
//        OrderService orderService = new OrderService();
//        orderService.order(2L);
        Dao<User> userDao = new UserDaoImpl();
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName("Lara");
        userDetails.setLastName("Croft");
        userDetails.setPhoneNumber("380991542388");
        User user = new User();
        user.setUserId(8L);
        user.setPassword("181818");
        user.setUserDetails(userDetails);
        userDao.updateElement(user);
//        Dao<User> userDao = new UserDaoImpl();
//        User user = new User();
//        user.setEmail("ponbtd@gmail.com");
//        user.setPassword("nvbfh119");
//        UserDetails userDetails = new UserDetails();
        //userDetails.setFirstName("Sofie");
        //userDetails.setLastName("Brown");
       // userDetails.setPhoneNumber("380775243624");
//        user.setUserDetails(userDetails);
//        userDao.addNewElement(user);
       // Persistence.createEntityManagerFactory()
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
        //User foundUser = em.find(User.class, 4L);
        //Product foundProduct = em.find(Product.class, 12L);
        //foundUser.getProducts().add(foundProduct);
//        System.out.println(foundProduct.getUsers().size());
//        em.getTransaction().commit();
//        em.close();
//        UserDetails user = new UserDetails();
//        user.setFirstName("Mike");
//        user.setLastName("Jonson");
//        user.setPhoneNumber("380957346121");
//        em.getTransaction().begin();
//        UserDetails user1 = em.find(UserDetails.class, 7L);
//        user1.setFirstName(user.getFirstName());
//        //user1 = user;
//        //em.persist(user);
//        em.getTransaction().commit();
        //UserDetails user1 = em.find(UserDetails.class, 4L);
//        List<UserDetails> userDetails = em.createQuery("SELECT u FROM UserDetails u", UserDetails.class).getResultList();
//        em.close();
        //UserDetails u = (UserDetails)userDetails.get(0);
//       System.out.println(userDetails.get(0).getFirstName());
    }
}
