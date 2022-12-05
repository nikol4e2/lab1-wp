package mk.finki.ukim.service.impl;

import mk.finki.ukim.model.Order;
import mk.finki.ukim.repository.OrderRepository;
import mk.finki.ukim.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String ballonColor, String clientName, String adress) {
        Order order=new Order(ballonColor,clientName,adress,(long) orderRepository.gettAllOrders().size()  );
        orderRepository.addOrder(order);
        return order;
    }

    @Override
    public Order getOrder() {
        return orderRepository.gettAllOrders().get(orderRepository.gettAllOrders().size()-1);
    }

    @Override
    public Order getActiveOrder() {
        return orderRepository.getActiveOrder();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.gettAllOrders();
    }
}
