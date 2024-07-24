package com.example.cim.service;


import com.example.cim.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long customerId, Long itemId,Order order);
    Order cancelOrder(Long orderId);
    List<Order>allOrders();

}
