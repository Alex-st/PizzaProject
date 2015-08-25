
package ua.epam.rd.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.repository.PizzaRepository;

/**
 * Created by alex on 8/15/15.
 */
@Service
public class SimplePizzaService implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.getAllPizzas();
    }

    @Override
    public Long addPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @Override
    public Pizza getPizzaById(Long id) {
        return pizzaRepository.getPizzaById(id);
    }
    
}
