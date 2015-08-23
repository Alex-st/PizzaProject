/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd;

import java.util.List;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.service.OrderService;
import ua.epam.rd.service.PizzaService;

/**
 *
 * @author andrii
 */
public class AppRunner {
    public static void main(String[] args) {
        
        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext("repositoryContext.xml");
        
        appContext.getBeanDefinitionNames();
        
        PizzaService pizzaService = appContext.
                getBean("pizzaService", PizzaService.class);
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        
        OrderService orderService = appContext.
                getBean("orderService", OrderService.class);
        
        Order newOrder1 = orderService.createNewOrder();
        newOrder1.addItems(pizzas.get(0), pizzas.get(1));
        orderService.placeOrder(newOrder1);
        
        Order newOrder2 = orderService.createNewOrder();
        newOrder2.addItems(pizzas.get(0));
        orderService.placeOrder(newOrder2);
        
        List<Order> orders = orderService.getAllOrders();
        
        //orders.stream().forEach(System.out::println);
        
    }
    
}

























