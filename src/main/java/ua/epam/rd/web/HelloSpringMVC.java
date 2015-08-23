package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.rd.repository.PizzaRepository;
import ua.epam.rd.service.PizzaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by alex on 8/7/15.
 */
// controller - means that it will be among controllers - classes that
    //generate final data for jsp pages
@Controller("helloController")

// так як спрінг не знає який метод цього біна запускати, необхідно це явно задати
//    це здійснюється через імплементацію інтерфейсу Controller, тоді за замовчуванням
// буде запускатись саме метод handlerRequest
//public class HelloSpringMVC implements org.springframework.web.servlet.mvc.Controller{

public class HelloSpringMVC {

    // для зв"язку з Бд необхідно підключити PizzaService
    @Autowired
    private PizzaService pizzaService;

    //requestMapping - прив"язка цього контроллера до певного url
    @RequestMapping("/hello")
    //ResponseBody нівелює viewResolver, тобто не додає ніякі префікси до лінків
    @ResponseBody
    public String hello(){
        return "Hello springMVC";
    }

//    @RequestMapping(value="/", method = RequestMethod.GET)
//    public ModelAndView handleDefaultRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
////до додавання ресорвера "viewResolver" в webContext.xml
////        return new ModelAndView("/WEB-INF/jsp/hello.jsp", "msg", "Hello SpringMVC");
//
//  // після додавання ресорвера "viewResolver" в webContext.xml
//        // для передачі декількох параметрів в другому параметрі можна передати мапу з параметрами
//       return new ModelAndView("hello", "msg", new Date());
//    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String handleDefaultRequest(Model model) throws Exception {
        model.addAttribute("msg", new Date());
        // викликає ресолвер для jsp hello ???
        return "hello";
    }

    @RequestMapping(value="/pizzas", method = RequestMethod.GET)
    public String viewPizzas(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        // мaє бути jsp pizzas
        return "pizzas";
    }
}
