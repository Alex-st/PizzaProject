package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.applet.AppletIllegalArgumentException;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;
import ua.epam.rd.service.PizzaService;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;

/**
 * Created by alex on 8/10/15.
 */
@Controller
@RequestMapping("/pizza")
public class PizzaController extends AbstractPizzaController{

    // we move PizzaService initialization to AbstractPizzaController
//    @Autowired
//    PizzaController(PizzaService pizzaService) {
//        this.pizzaService = pizzaService;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAllPizzas(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());

        //added according to SrpingSecurity
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", auth.getName());
        model.addAttribute("roles", auth.getAuthorities().toString());
        //ThreadLocal thl = new ThreadLocal();

        return "pizzas";
    }

    @RequestMapping(value = "/createPizzaForm", method = RequestMethod.GET)
    public String showCreatePizzaForm() {
        //model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "createPizza";
    }

//    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
//    public String pizzaCreate(@RequestParam("pizzaName") String name, @RequestParam("pizzaType") PizzaType type,
//                              @RequestParam("pizzaPrice") double price, Model model) {
//        Pizza temp = new Pizza();
//        temp.setName(name);
//        temp.setType(type);
//        temp.setPrice(price);
//
//        pizzaService.addPizza(temp);
//
//        model.addAttribute("pizzas", pizzaService.getAllPizzas());
//        return "pizzas";
//    }

    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public String pizzaCreate(@ModelAttribute Pizza newPizza) {

        pizzaService.addPizza(newPizza);

//        pizzaService.getAllPizzas());
        return "redirect:";
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String pizzaEdit(@RequestParam("pizzaId") Pizza pizza, Model model) {

        //Pizza pizza = pizzaService.getPizzaById(id);
//
//        if (pizza == null)
//            throw new NotFoundPizzaException("Pizza not found with id:"+id);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());


        model.addAttribute("pizza", pizza);

//        pizzaService.getAllPizzas());
        return "createPizza";
    }

//    @ExceptionHandler(NotFoundPizzaException.class)  // реєструємо, що даний клас має спрацьовувати на даний тип ексепшенів
//    public ModelAndView exceptionHandler(Exception exception, HttpServletRequest request) {
//
//        ModelAndView model = new ModelAndView("error"); // назва jsp
//
//        model.addObject("ex", exception);
//        model.addObject("url", request.getRequestURL());
//
//        return model;
//    }
//
//    @RequestMapping(value = "/createNewOrder", method = RequestMethod.GET)
//    public String createOrderForm(Model model) {
//        return "createOrder";
//    }
//
//    protected Pizza getPizzaById(Long id) {
//
//
//        return new Pizza();
//    }

//    @InitBinder
//    private void pizzaBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Pizza.class, new PropertyEditorSupport(){
//            @Override
//            public void setAsText(String pizzaId) {
//            Pizza pizza = null;
//                if (pizzaId != null && !pizzaId.trim().isEmpty()) {
//                    Long id = Long.valueOf(pizzaId);
//                    pizza = getPizzaById(id);
//                }
//            }
//        });
//    }


}
