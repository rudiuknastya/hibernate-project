package jdbcDao;

import java.util.List;

public interface ShoppingCartDao {
    public void addProduct(Long userId, Long productId);
    public void deleteProduct(Long userId, Long productId);
    public List<Product> getUserProducts(Long userId);
    public void deleteUserProducts(Long userId);

}
