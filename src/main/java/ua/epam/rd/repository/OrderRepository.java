/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.repository;

import java.util.List;
import java.util.Map;

import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Pizza;


public interface OrderRepository {

    public int getNewOrderID();
    
    List<Order> getAllOrders();
    
    Order getOrderById(Long id);
   
    Order save(Order order);

    public void update(Order order);

    public Order createNewOrder(Map<Pizza, Integer> orderMap);
    
}
