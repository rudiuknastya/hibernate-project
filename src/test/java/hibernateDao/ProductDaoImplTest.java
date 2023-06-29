package hibernateDao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoImplTest {
    Dao<Product> productDao = new ProductDaoImpl();
    @Test
    public void insertProduct(){
        Product product = new Product();
        product.setProductName("Apple MacBook Pro 14\" M2");
        product.setProductType("2");
        product.setPrice(34.0);
        productDao.addNewElement(product);
    }
    @Test
    public void getAllProducts(){
        List<Product> products = productDao.getAllElements();
        assertEquals(25, products.size());
        assertNotNull(products.get(12));
        assertEquals("Motorola G32", products.get(15).getProductName());
        assertEquals("monitor", products.get(9).getProductType());
        assertEquals(4.61, products.get(12).getPrice(), 0);
    }

    @Test
    public void getProductById(){
        Product product = productDao.getElementById(11L);
        assertNotNull(product);
        assertEquals("HP V24 FHD", product.getProductName());
        assertEquals("monitor", product.getProductType());
        assertEquals(4.89, product.getPrice(), 0);
    }
    @Test
    public void getProductByIdOutOfTable(){
        Product product = productDao.getElementById(30L);
        assertNull(product);
    }

    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setProductId(10L);
        product.setProductName("Apple MacBook Pro 14\" M2");
        product.setProductType("2");
        product.setPrice(34.0);
        productDao.updateElement(product);
    }

    @Test
    public void deleteProduct(){
        productDao.deleteElement(27L);
    }

}