package com.example.cim.service.serviceImpl;

import com.example.cim.model.Customer;
import com.example.cim.model.Item;
import com.example.cim.model.Order;
import com.example.cim.model.constants.OrderStatus;
import com.example.cim.repository.CustomerRepository;
import com.example.cim.repository.ItemRepository;
import com.example.cim.repository.OrderRepository;
import com.example.cim.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Order createOrder(Long customerId, Long itemId,Order order) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Item> item = itemRepository.findById(itemId);

        if(customer.isEmpty()){
            throw  new RuntimeException("Customer with id: "+customerId+" does not exist");
        }

        if (item.isEmpty()){
            throw  new RuntimeException("Item with id: "+itemId+" does not exist");
        }

        if (item.get().getQuantityAvailable() == 0){
            throw  new RuntimeException("Item with id: "+itemId+" is out of stock");
        }

        order.setItemOrder(item.get());
        order.setCustomerOrder(customer.get());
        order.setQuantity(order.getQuantity());
        order.setOrderStatus(OrderStatus.PENDING.name());

        return  orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()){
            throw  new RuntimeException("Order with id: "+orderId+" does not exist");
        }

        order.get().setOrderStatus(OrderStatus.CANCELLED.name());
      return   orderRepository.save(order.get());
    }

    @Override
    public List<Order> allOrders(){

        return orderRepository.findAll();
    }
}
