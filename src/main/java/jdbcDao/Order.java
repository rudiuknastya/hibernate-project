package jdbcDao;


public class Orders {
    private Long orderId;
    private Long userId;
    private String products;
    private Double productsPrice;
    private String orderTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(Double productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

}
