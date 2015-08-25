
package ua.epam.rd.repository;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.OrderStatus;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.Users;

/**
 * Created by alex on 8/15/15.
 */
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

    public void createNewOrder() {

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
        temp.setOrderItems(order.getOrderItems());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Order createNewOrder(Map<Pizza, Integer> orderMap, Users user) {
        Order newOrder = new Order();
        newOrder.setOrderItems(orderMap);
        newOrder.setOrderStatus(OrderStatus.NEW);
        newOrder.setCustomer(user);
        em.persist(newOrder);
        return newOrder;
    }

}
