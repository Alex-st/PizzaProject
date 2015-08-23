/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.service;

import java.util.List;
import java.util.Map;

import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface OrderService {
    
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createNewOrder();
    Order placeOrder(Map<Pizza, Integer> order);
    public boolean cancelOrder(Long orderId, String email);
    
}
