package mk.finki.ukim.service;

import mk.finki.ukim.model.Order;
import org.springframework.stereotype.Service;


public interface OrderService {
    Order placeOrder(String ballonColor, String clientName, String adress);
    Order getOrder();
}
