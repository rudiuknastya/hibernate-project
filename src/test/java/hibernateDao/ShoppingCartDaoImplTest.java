package hibernateDao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartDaoImplTest {
    ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
    @Test
    public void addProductToCart(){
        shoppingCartDao.addProduct(1L, 4L);
        Dao<User> userDao = new UserDaoImpl();
        User user = userDao.getElementById(2L);
        Dao<Product> productDaoDao = new ProductDaoImpl();
        Product product = productDaoDao.getElementById(8L);
        assertEquals(2, product.getUsers().size());
        assertEquals(2, user.getProducts().size());
    }
    @Test
    public void deleteProductFromCart(){
        shoppingCartDao.deleteProduct(1L, 4L);
        Dao<User> userDao = new UserDaoImpl();
        User user = userDao.getElementById(1L);
        Dao<Product> productDaoDao = new ProductDaoImpl();
        Product product = productDaoDao.getElementById(4L);
        assertEquals(0, product.getUsers().size());
        assertEquals(2, user.getProducts().size());
    }

    @Test
    public void getUserProducts(){
        List<Product> productList = shoppingCartDao.getUserProducts(2L);
        assertEquals(3, productList.size());
        assertEquals("Samsung Galaxy Tab A8", productList.get(0).getProductName());
    }

    @Test
    public void deleteAllUserProducts(){
        shoppingCartDao.deleteAllUserProducts(1L);
        Dao<User> userDao = new UserDaoImpl();
        User user = userDao.getElementById(1L);
        Dao<Product> productDaoDao = new ProductDaoImpl();
        Product product = productDaoDao.getElementById(8L);
        assertEquals(1, product.getUsers().size());
        assertEquals(0, user.getProducts().size());
    }
}