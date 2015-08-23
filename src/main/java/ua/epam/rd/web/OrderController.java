package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.epam.rd.domain.Order;
import ua.epam.rd.service.OrderService;
import ua.epam.rd.service.PizzaService;

/**
 * Created by alex on 8/14/15.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    protected OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAllPizzas(Model model) {
//        model.addAttribute("pizzas", pizzaService.getAllPizzas());
//
//        //added according to SrpingSecurity
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        model.addAttribute("name", auth.getName());
//        model.addAttribute("roles", auth.getAuthorities().toString());
//        //ThreadLocal thl = new ThreadLocal();

        return "createOrder";
    }

}
