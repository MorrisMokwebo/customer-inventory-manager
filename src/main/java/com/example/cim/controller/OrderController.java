package com.example.cim.controller;

import com.example.cim.model.Order;
import com.example.cim.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder/{customerId}/{itemId}")
    public Order createOrder(@PathVariable Long customerId, @PathVariable Long itemId, @RequestBody Order order){
        return orderService.createOrder(customerId,itemId,order);
    }

    @GetMapping("/allOrders")
    public List<Order>getAllOrders(){
        return orderService.allOrders();
    }

    @PutMapping("/cancelOrder/{orderId}")
    public Order cancelOrder(@PathVariable Long orderId){
       return orderService.cancelOrder(orderId);
    }
}
