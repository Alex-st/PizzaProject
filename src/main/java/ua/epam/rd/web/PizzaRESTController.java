package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.service.PizzaService;

/**
 * Created by alex on 8/12/15.
 */

//@Controller
//public class PizzaRESTController {
//
//    @RequestMapping(method = RequestMethod.GET, value = "hi")
//    @ResponseBody
//    public String hello() {
//        return "Hello from RESTController";
//    }
//}

@RestController
public class PizzaRESTController extends AbstractPizzaController{


    @RequestMapping(method = RequestMethod.GET, value = "hi")
    public ResponseEntity<String> hello() {
        //return "Hello from RESTController";
        return new ResponseEntity<>("Hello from Rest", HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/pizza/{pizzaId}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("pizzaId") Pizza pizza) {
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(pizza);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/pizza", method = RequestMethod.POST, headers = "Content-Type=application/json ")
    public ResponseEntity<?> createNewPizza(@RequestBody Pizza pizza, UriComponentsBuilder builder) {
        System.out.println(pizza);
        pizzaService.addPizza(pizza);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/pizza/{id}").buildAndExpand(pizza.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
