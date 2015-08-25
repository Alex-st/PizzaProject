package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.Users;
import ua.epam.rd.service.OrderService;
import ua.epam.rd.service.PizzaService;
import ua.epam.rd.service.UsersService;

import java.beans.PropertyEditorSupport;
import java.util.*;

/**
 * Created by alex on 8/14/15.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected PizzaService pizzaService;

    @Autowired
    protected UsersService usersService;

    private void fillUsersParameters(Authentication auth, Model model) {
        model.addAttribute("name", auth.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
        model.addAttribute("balance", usersService.getUserBalance(usersService.getUserByLogin(auth.getName())));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAllPizzas(Model model) {

        List<Pizza> pizzas = pizzaService.getAllPizzas();

        Map<Pizza, Integer> entities = new HashMap<>();

        for(Pizza i: pizzas) {
            entities.put(i, 0);
        }

        model.addAttribute("map", entities);
//
//        //added according to SrpingSecurity
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        fillUsersParameters(auth, model);
//        model.addAttribute("name", auth.getName());
//        model.addAttribute("roles", auth.getAuthorities().toString());
//        model.addAttribute("balance", usersService.getUserBalance(usersService.getUserByLogin(auth.getName())));
//        //ThreadLocal thl = new ThreadLocal();

        return "createOrder";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public String placeOrder(@RequestParam Map<String,String> allRequestParams, Model model) {

        //map for storing pizzas in order
        Map<Pizza, Integer> map = new HashMap<>();
        //Price of order
        Double orderPrice = 0.;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users customer = usersService.getUserByLogin(auth.getName());

        for(Map.Entry<String, String> i: allRequestParams.entrySet()) {

            //our form also has hidden crf field, so we need to check whether parameter is Integer or not
            try {
                Integer.parseInt(i.getKey());
            } catch (NumberFormatException e) {
                System.out.println("Wrong number");
                continue;
            }

            if (Integer.valueOf(i.getValue()) < 1) {
                continue;
            }
            //counting current order price without discount
            Pizza tempPizza = pizzaService.getPizzaById(Long.valueOf(i.getKey()));
            orderPrice += tempPizza.getPrice()*Integer.valueOf(i.getValue());
            map.put(tempPizza, Integer.valueOf(i.getValue()));

        }
        //сounting current order price with discount
        orderPrice -= usersService.getUserBalance(customer)*0.1;
        model.addAttribute("OrderPrice", orderPrice);

        orderService.placeOrder(map, customer);
        usersService.increaseCustomerBalance(customer, orderPrice);


        return "redirect:";
    }

    @RequestMapping(value = "/showUserOrders", method = RequestMethod.GET)
    public String showAllUserOrders(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Set<Order> orders = usersService.getAllUserOrders(usersService.getUserByLogin(auth.getName()));

        model.addAttribute("orders", orders);
        fillUsersParameters(auth, model);

        return "orderList";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/showAllOrders", method = RequestMethod.GET)
    public String showAllOrders(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Order> orders = orderService.getAllOrders();

        for (Order i: orders) {
            System.out.println(i.getOrderId());
            for(Map.Entry<Pizza, Integer> j: i.getOrderItems().entrySet()) {
                System.out.println(j.getKey().getName());
                System.out.println(j.getValue());
            }
        }
        //System.out.println(orders.size());

        model.addAttribute("orders", orders);
        fillUsersParameters(auth, model);

        return "orderList";
    }
}
