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
import ua.epam.rd.domain.PizzaType;
import ua.epam.rd.repository.OrderRepository;
import ua.epam.rd.repository.PizzaRepository;

/**
 *
 * @author andrii
 */
public class AppJPARunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext repositoryContext
                = new ClassPathXmlApplicationContext("repositoryContext.xml");

        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"},
                        repositoryContext);

        PizzaRepository pizzaRepository
                = appContext.getBean("pizzaRepository", PizzaRepository.class);

        for (Pizza p : pizzaRepository.getAllPizzas()){
            System.out.println(p);
        }
        
        
        
        Pizza pizza = new Pizza();
        pizza.setName("Oliva");
        pizza.setType(PizzaType.Sea);
        pizza.setPrice(123.1);

        System.out.println("Before: " + pizza);

        pizzaRepository.save(pizza);

        System.out.println("After: " + pizza);

        Pizza pizza2 = new Pizza();
        pizza2.setName("Meat");
        pizza2.setType(PizzaType.Meat);
        pizza2.setPrice(130.2);

//        pizzaRepository.save(pizza2);

        OrderRepository orderRepository
                = appContext.getBean("orderRepository", OrderRepository.class);

//        Order order = new Order();
//        order.addItems(pizza, pizza, pizza2);
//        orderRepository.save(order);

        List<Order> orders = orderRepository.getAllOrders();
        //orders.stream().forEach(System.out::println);
        //orders.get(0).getItems().size();
        System.out.println("------");
        Order order = orderRepository.getOrderById(79L);
        System.out.println(order);
        //order.getItems().keySet().stream().forEach(System.out::println);
        
        appContext.close();
        repositoryContext.close();
    }
}
