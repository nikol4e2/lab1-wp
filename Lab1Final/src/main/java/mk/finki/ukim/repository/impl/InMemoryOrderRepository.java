package mk.finki.ukim.repository.impl;

import mk.finki.ukim.bootstrap.DataHolder;
import mk.finki.ukim.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOrderRepository {





    public List<Order> gettAllOrders()
    {
        return DataHolder.orders;
    }

    public void addOrder(Order order)
    {
        DataHolder.orders.add(order);

    }
    public Order getActiveOrder()
    {
        return DataHolder.orders.get(DataHolder.orders.size()-1);
    }
}
