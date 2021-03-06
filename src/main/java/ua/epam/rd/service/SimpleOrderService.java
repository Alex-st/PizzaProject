
package ua.epam.rd.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.OrderStatus;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.Users;
import ua.epam.rd.repository.OrderRepository;

/**
 * Created by alex on 8/15/15.
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
     return new Order();
//        return null;
    }

    boolean isWorkingDay() {
//        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
//        return dayOfWeek != DayOfWeek.SUNDAY;
        return true;
    }

    @Override
    @Transactional
    public Order placeOrder(Map<Pizza, Integer> map, Users user) {
        if (!isWorkingDay()) {
            throw new IllegalStateException();
        }

        if (map.keySet().isEmpty()) {
            System.out.println("Map keyset is empty");
            throw new IllegalArgumentException();
        }

        return orderRepository.createNewOrder(map, user);
        //return orderRepository.save(order);
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
