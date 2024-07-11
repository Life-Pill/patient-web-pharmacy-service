package com.lifepill.pharmacyservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifepill.pharmacyservice.model.Order;
import com.lifepill.pharmacyservice.repo.OrderRepository;
import com.lifepill.pharmacyservice.util.MissingParameterException;
import com.lifepill.pharmacyservice.util.ResourceNotFoundException;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    // get order
    public Order getOrder(Long pharmacyId, Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty() || order.get().getPharmacyId() != pharmacyId) {
            throw new ResourceNotFoundException("Pharmacy with ID " + orderId + " not found.");
        } else {
            Order existingOrder = order.get();
            return existingOrder;
        }
    }

    // get all orders
    public List<Order> getAllOrders(Long pharmacyId) {
        return orderRepository.findByPharmacyId(pharmacyId);
    }

    // add new order
    public Order addOrder(Order newOrder) {
        if (newOrder.getPharmacyId() == null) {
            throw new MissingParameterException("Pharmacy Id cannot be Empty");
        }

        if (newOrder.getCustomerId() == null) {
            throw new MissingParameterException("Customer Id cannot be Empty");
        }

        if (newOrder.getPaymentAmount() == 0.0) {
            throw new MissingParameterException("Amount cannot be Empty");
        }

        newOrder.setOrderStatus(false);
        newOrder.setCreatedOn(LocalDateTime.now());
        newOrder.setUpdatedOn(LocalDateTime.now());

        return orderRepository.save(newOrder);
    }

    // update order details
    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + orderId + " not found.");
        }

        Order existingOrder = order.get();

        if (existingOrder.getPaymentAmount() == 0.0) {
            throw new MissingParameterException("Amount cannot be Empty");
        }

        existingOrder.setPaymentAmount(updatedOrder.getPaymentAmount());
        existingOrder.setUpdatedOn(LocalDateTime.now());

        return orderRepository.save(existingOrder);
    }

    // update order status
    public Order updateOrderStatus(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + orderId + " not found.");
        }

        Order existingOrder = order.get();

        // toggling the order status
        existingOrder.setOrderStatus(!existingOrder.isOrderStatus());

        existingOrder.setUpdatedOn(LocalDateTime.now());

        return orderRepository.save(existingOrder);
    }

    // delete order
    public void deleteOrder(Long pharmacyId, Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        // Order Id not found error handling
        if (order.isEmpty() || order.get().getPharmacyId() != pharmacyId) {
            throw new ResourceNotFoundException("Order with ID " + orderId + " not found.");
        }

        orderRepository.deleteById(orderId);
    }
}
