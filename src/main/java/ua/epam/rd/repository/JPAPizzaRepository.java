package ua.epam.rd.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.PizzaType;

@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext(name = "HiberanteMySQL")
    private EntityManager em;

    @Override
    public List<Pizza> getAllPizzas() {
        TypedQuery<Pizza> query
                = em.createQuery("select p from Pizza p", Pizza.class);
        return query.getResultList();
    }

    @Override
    public List<Pizza> getPizzasByType(PizzaType type) {
        TypedQuery<Pizza> query
                = em.createQuery("select p from Pizza p where p.type = type", Pizza.class);
        return query.getResultList();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public Long save(Pizza pizza) {

        //System.out.println("JPAPizzaRepository:"+pizza);
        if (pizza.getId()!=null) {
            Pizza temp = em.find(Pizza.class, pizza.getId());
            temp.setName(pizza.getName());
            temp.setPrice(pizza.getPrice());
            temp.setType(pizza.getType());
            em.merge(temp);
        }
        else
            em.persist(pizza);

        return pizza.getId();
    }

    @Override
    public Pizza getPizzaById(Long id) {
        return em.find(Pizza.class, id);
    }

}
