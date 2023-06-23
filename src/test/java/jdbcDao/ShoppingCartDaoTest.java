package jdbcDao;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartDaoTest {
    @Test
    public void addProductInCart() throws SQLException {
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(3L);
        shoppingCart.setProductId(12L);
        assertEquals("Added",shoppingCartDao.addProduct(shoppingCart));

    }
    @Test
    public void deleteProductFromCart() throws SQLException {
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(3L);
        shoppingCart.setProductId(12L);
        assertEquals("Deleted",shoppingCartDao.deleteProduct(shoppingCart));
    }
    @Test
    public void getUserProducts() throws SQLException {
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
        List<Long> products = shoppingCartDao.getUserProducts(1L);
        assertEquals(3,products.size());
    }
    @Test
    public void deleteUserProducts() throws SQLException {
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
        assertEquals("Deleted",shoppingCartDao.deleteUserProducts(5L));
    }


}