package hibernateDao;

import java.util.List;

public interface ShoppingCartDao {
    public void addProduct(Long userId, Long productId);
    public void deleteProduct(Long userId, Long productId);
    public List<Product> getUserProducts(Long userId);
    public String deleteAllUserProducts(Long userId);
}
