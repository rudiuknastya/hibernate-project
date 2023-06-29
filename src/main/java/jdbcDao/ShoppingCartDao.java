package jdbcDao;

import java.util.List;

public interface ShoppingCartDao {
    public String addProduct(Long userId, Long productId);
    public String deleteProduct(Long userId, Long productId);
    public List<Product> getUserProducts(Long userId);
    public String deleteUserProducts(Long userId);

}
