package hibernateDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDaoImpl implements Dao<Product>{
    @Override
    public void addNewElement(Product product) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Product> getAllElements() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        em.close();
        return products;
    }

    @Override
    public Product getElementById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.getTransaction().commit();
        return product;
    }

    @Override
    public void updateElement(Product product) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteElement(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }
}
