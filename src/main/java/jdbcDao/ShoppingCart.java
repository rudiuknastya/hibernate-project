package jdbcDao;

public class ShoppingCart {
    private Long userId;
    private Long productId;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public long getProductId() {
        return productId;
    }
}
