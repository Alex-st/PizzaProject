/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.rd.domain.Order;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext(name = "HiberanteMySQL") //, type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public int getNewOrderID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        TypedQuery<Order> query = 
                em.createQuery("select o from Order o", Order.class);
        return query.getResultList();
    }

    @Override    
    @Transactional
    public Order getOrderById(Long id) {

        Order order = (Order) em.find(Order.class, id);
        //order.getItems().size();

       // if (order == null) return null;

        return order;
    }

    @Override
    public Order save(Order order) {
        em.persist(order);
        return order;
    }

    @Override
    public void update(Order order) {
        Long id = order.getOrderId();
        Order temp = (Order) em.find(Order.class, id);
        temp.setDate(order.getDate());
        temp.setOrderStatus(order.getOrderStatus());
        temp.setItems(order.getItems());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
