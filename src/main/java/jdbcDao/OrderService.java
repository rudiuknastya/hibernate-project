package jdbcDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService {
    public void order(Long userId){
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
        List<Product> userProducts = shoppingCartDao.getUserProducts(userId);
        String products = "";
        int i = 0;
        Double productsPrice = 0.0;
        for(Product product: userProducts){
            if(i == userProducts.size()-1){
                products += product.getProductName();
            } else {
                products += product.getProductName() + ", ";
            }
            productsPrice += product.getPrice();
            i++;
        }
        Order order = createOrder(userId, products, productsPrice);
        OrderDao ordersDao = new OrderDaoImpl();
        ordersDao.addOrder(order);
        shoppingCartDao.deleteUserProducts(userId);
    }

    private Order createOrder(Long userId, String products, Double productsPrice){
        Order order = new Order();
        order.setUserId(userId);
        order.setProducts(products);
        order.setProductsPrice(productsPrice);
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        order.setOrderTime(myDate.format(myFormatObj));
        return order;
    }
}
