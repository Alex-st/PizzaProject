
package ua.epam.rd.service;

import java.util.List;
import ua.epam.rd.domain.Pizza;

/**
 * Created by alex on 8/15/15.
 */
public interface PizzaService {
    List<Pizza> getAllPizzas();
    Long addPizza(Pizza pizza);
    Pizza getPizzaById(Long id);
}
