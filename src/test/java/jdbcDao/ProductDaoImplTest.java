package jdbcDao;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoImplTest {
    Dao<Product> productDao = new ProductDaoImpl();
    @Test
    public void insertProduct(){
        Product product = new Product();
        product.setProductName("Asus VZ279HE");
        product.setProductType("5");
        product.setPrice(12.87);
        productDao.addNewElement(product);
    }
    @Test
    public void getAllProducts(){
        List<Product> products = productDao.getAllElements();
        assertEquals(25, products.size());
        assertEquals("Xiaomi Redmi Note 12 Pro", products.get(0).getProductName());
        assertEquals("laptop", products.get(22).getProductType());
        assertEquals(4.61, products.get(12).getPrice(), 0);
    }

    @Test
    public void getProductById(){
        Product product = productDao.getElementById(10L);
        assertEquals("Apple MacBook Pro 14\" M2", product.getProductName());
        assertEquals("laptop", product.getProductType());
        assertEquals(34.0, product.getPrice(), 0);
    }

    @Test
    public void updateProduct(){
        Product product = productDao.getElementById(11L);
        product.setProductType("1");
        product.setPrice(14.87);
        productDao.updateElement(product);
        Product product1 = productDao.getElementById(11L);
        assertEquals("smartphone", product1.getProductType());
        assertEquals(14.87, product1.getPrice(), 0);
    }

    @Test
    public void deleteProduct(){
        productDao.deleteElement(28L);
    }
}