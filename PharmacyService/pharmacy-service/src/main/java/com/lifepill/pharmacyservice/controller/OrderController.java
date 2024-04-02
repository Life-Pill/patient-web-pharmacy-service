package com.lifepill.pharmacyservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifepill.pharmacyservice.model.Order;
import com.lifepill.pharmacyservice.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // get order
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Get a specific order")
    @GetMapping("/{pharmacyId}/{orderId}")
    public Optional<Order> getOrder(@PathVariable Long pharmacyId, @PathVariable Long orderId) {
        return getOrder(pharmacyId, orderId);
    }

    // get all orders
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Get all the orders")
    @GetMapping("/{pharmacyId}")
    public List<Order> getAllOrders(@PathVariable Long pharmacyId) {
        return orderService.getAllOrders(pharmacyId);
    }

    // add new order
    @Tag(name = "post", description = "POST methods of Pharmacy service API")
    @Operation(description = "Create a new order")
    @PostMapping
    public Order addOrder(@RequestBody Order newOrder) {
        return orderService.addOrder(newOrder);
    }

    // update order
    @Tag(name = "put", description = "PUT methods of Pharmacy service API")
    @Operation(description = "Update order details")
    @PutMapping("/{orderId}")
    public Order updateOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(orderId, updatedOrder);
    }

    // update order status
    @Tag(name = "put", description = "PUT methods of Pharmacy service API")
    @Operation(description = "Update order status")
    @PutMapping("/status/{orderId}")
    public Order updateOrderStatus(@PathVariable Long orderId) {
        return orderService.updateOrderStatus(orderId);
    }

    // delete order
    @Tag(name = "delete", description = "DELETE methods of Pharmacy service API")
    @Operation(description = "Delete a specific order")
    @DeleteMapping("/{pharmacyId}/{orderId}")
    public String deleteOrder(Long pharmacyId, Long orderId) {
        orderService.deleteOrder(pharmacyId, orderId);
        return "Order successfully deleted";
    }
}
