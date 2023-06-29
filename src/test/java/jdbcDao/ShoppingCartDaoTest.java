package jdbcDao;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartDaoTest {
    ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
    @Test
    public void addProductInCart() throws SQLException {
        shoppingCartDao.addProduct(4L, 18L);
    }
    @Test
    public void deleteProductFromCart() throws SQLException {
        shoppingCartDao.deleteProduct(1L, 24L);
    }
    @Test
    public void getUserProducts() throws SQLException {
        List<Product> products = shoppingCartDao.getUserProducts(4L);
        assertEquals(3,products.size());
        assertEquals("LG 32LQ630B6LA", products.get(0).getProductName());
        assertEquals("smartphone",products.get(1).getProductType());
        assertEquals(2.0,products.get(2).getPrice(),0);
    }
    @Test
    public void deleteUserProducts() throws SQLException {
        shoppingCartDao.deleteUserProducts(4L);
    }


}