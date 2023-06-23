package jdbcDao;


public class Orders {
    private long orderId;
    private long userId;
    private String products;
    private double productsPrice;
    private String orderTime;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(double productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
//    public void setOrderTime(String) {
//        LocalDateTime myDateObj = LocalDateTime.now();
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        orderTime = myDateObj.format(myFormatObj);
//    }
}
