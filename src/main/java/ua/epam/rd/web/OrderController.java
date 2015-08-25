package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("name", auth.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
//        //ThreadLocal thl = new ThreadLocal();

        return "createOrder";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public String placeOrder(@RequestParam Map<String,String> allRequestParams, Model model) {

        Map<Pizza, Integer> map = new HashMap<>();
        Double orderPrice = 0.;

        for(Map.Entry<String, String> i: allRequestParams.entrySet()) {
            System.out.println(i.getKey());

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
            Pizza tempPizza = pizzaService.getPizzaById(Long.valueOf(i.getKey()));
            orderPrice += tempPizza.getPrice()*Integer.valueOf(i.getValue());
            map.put(tempPizza, Integer.valueOf(i.getValue()));

        }

        model.addAttribute("OrderPrice", orderPrice);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

//        System.out.println("auth name: "+auth.getName());

        //Users customer = usersService.getUserByLogin(auth.getName());

        //System.out.println("bd name: "+customer.getName());

        orderService.placeOrder(map, usersService.getUserByLogin(auth.getName()));

        return "redirect:";
    }



}
