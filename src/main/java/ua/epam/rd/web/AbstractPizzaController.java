package ua.epam.rd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.service.PizzaService;

import java.beans.PropertyEditorSupport;

/**
 * Created by alex on 8/12/15.
 */
abstract public class AbstractPizzaController {

    @Autowired
    protected PizzaService pizzaService;

    protected Pizza getPizzaById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID<0");
        }
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            throw new NotFoundPizzaException("Pizza id" + id + " not found");
        }
        return pizza;
    }

    @InitBinder
    private void pizzaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String pizzaId) {
                        Pizza pizza = null;
                        if (pizzaId != null && !pizzaId.trim().isEmpty()) {
                            Long id = Long.valueOf(pizzaId);

                            System.out.println(id);

                            pizza = getPizzaById(id);
                        }
                        setValue(pizza);
                        System.out.println(pizza);
                    }
                }
        );
    }
}
