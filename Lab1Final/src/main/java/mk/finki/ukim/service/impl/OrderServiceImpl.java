package mk.finki.ukim.service.impl;

import mk.finki.ukim.model.Order;
import mk.finki.ukim.repository.impl.InMemoryOrderRepository;
import mk.finki.ukim.repository.jpa.OrderRepository;
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
        Order order=new Order(ballonColor );
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order getOrder() {
        return orderRepository.findAll().get(orderRepository.findAll().size()-1);
    }

    @Override
    public Order getActiveOrder() {
        return orderRepository.findAll().get(orderRepository.findAll().size()-1);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
