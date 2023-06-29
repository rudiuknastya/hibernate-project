package hibernateDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService {
    public void order(Long userId){
        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoImpl();
        List<Product> productList = shoppingCartDao.getUserProducts(userId);
        String products = "";
        Double productsPrice = 0.0;
        int i = 0;
        for(Product product: productList){
            if(i == productList.size()-1) {
                products += product.getProductName();
            }else {
                products += product.getProductName() + ", ";
            }
            productsPrice += product.getPrice();
        }
        OrdersDao ordersDao = new OrdersDaoImpl();
        Dao<User> userDao = new UserDaoImpl();
        User user = userDao.getElementById(userId);
        Orders order = new Orders();
        order.setUser(user);
        order.setProducts(products);
        order.setProductsPrice(productsPrice);
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        order.setOrderTime(myDate.format(myFormatObj));
        ordersDao.addOrder(order);
        shoppingCartDao.deleteAllUserProducts(userId);
    }
}
