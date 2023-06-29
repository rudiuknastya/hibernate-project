package hibernateDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ShoppingCartDaoImpl implements ShoppingCartDao{
    @Override
    public void addProduct(Long userId, Long productId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User foundUser = em.find(User.class, userId);
        Product foundProduct = em.find(Product.class, productId);
        foundUser.getProducts().add(foundProduct);
        foundProduct.getUsers().add(foundUser);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteProduct(Long userId, Long productId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User foundUser = em.find(User.class, userId);
        Product foundProduct = em.find(Product.class, productId);
        foundUser.getProducts().remove(foundProduct);
        foundProduct.getUsers().remove(foundUser);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Product> getUserProducts(Long userId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, userId);
        return user.getProducts();
    }

    @Override
    public String deleteAllUserProducts(Long userId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User foundUser = em.find(User.class, userId);
        foundUser.getProducts().clear();
        em.getTransaction().commit();
        em.close();
        return null;
    }
}
