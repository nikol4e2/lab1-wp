package mk.finki.ukim.repository;

import mk.finki.ukim.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    List<Order> orders=new ArrayList<>();



    public List<Order> gettAllOrders()
    {
        return orders;
    }

    public void addOrder(Order order)
    {
        orders.add(order);

    }
}
