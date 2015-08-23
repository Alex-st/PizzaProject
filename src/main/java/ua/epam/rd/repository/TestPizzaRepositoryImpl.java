/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.repository;

import java.util.List;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;

/**
 *
 * @author andrii
 */
public class TestPizzaRepositoryImpl implements PizzaRepository {

    public List<Pizza> getAllPizzas() {
        return null;
    }
    
    public List<Pizza> getPizzasByType(PizzaType type) {
        return null;
    }

    @Override
    public Long save(Pizza pizza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pizza getPizzaById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
