package jdbcDao;

import java.util.List;

public interface ShoppingCartDao {
    public String addProduct(ShoppingCart cart);
    public String deleteProduct(ShoppingCart cart);
    public List<Long> getUserProducts(Long userId);
    public String deleteUserProducts(Long userId);

}
