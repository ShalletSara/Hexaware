package dao;

import entity.Order;
import java.util.List;

public interface OrderDAO {
    void placeOrder(Order order);
    List<Order> getOrderHistory(String userID);
}
