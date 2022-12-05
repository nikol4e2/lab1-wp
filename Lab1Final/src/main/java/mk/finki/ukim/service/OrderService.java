package mk.finki.ukim.service;

import mk.finki.ukim.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    Order placeOrder(String ballonColor, String clientName, String adress);
    Order getOrder();
    Order getActiveOrder();
    List<Order> getAllOrders();
}
