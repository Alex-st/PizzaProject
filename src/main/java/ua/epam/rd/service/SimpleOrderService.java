/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.service;

//import java.time.DayOfWeek;
//import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.OrderStatus;
import ua.epam.rd.repository.OrderRepository;

/**
 *
 * @author andrii
 */
@Service("orderService")
public class SimpleOrderService implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    //@Transactional
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public Order createNewOrder() {
        int newOrdeId = orderRepository.getNewOrderID();
        Order newOrder = createEmpyOrder();
        return newOrder;

    }

    @Lookup(value = "order")
    public Order createEmpyOrder() {
        return null;
    }

    boolean isWorkingDay() {
//        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
//        return dayOfWeek != DayOfWeek.SUNDAY;
        return true;
    }

    @Override
    @Transactional
    public Order placeOrder(Order order) {
        if (!isWorkingDay()) {
            throw new IllegalStateException();
        }

        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return orderRepository.save(order);
    }
    
    @Override
    public boolean cancelOrder(Long orderId, String email) {
        Order order = orderRepository.getOrderById(orderId);
        //order.status != Done, Cancel
        //order.status = Cancel
        order.setOrderStatus(OrderStatus.CANCELD);
        orderRepository.update(order);
        return true;
    }

}
