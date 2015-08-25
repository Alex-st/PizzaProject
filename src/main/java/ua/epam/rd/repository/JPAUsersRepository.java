package ua.epam.rd.repository;

import org.springframework.stereotype.Repository;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 8/25/15.
 */
@Repository("usersRepository")
public class JPAUsersRepository implements UsersRepository {

    @PersistenceContext(name = "HiberanteMySQL")
    private EntityManager em;

    @Override
    public Users getUserByLogin(String login) {

//        System.out.println("jpaUsersRepository:login:"+login);

        TypedQuery<Users> query
                = em.createQuery("select u from Users u where u.login = :login", Users.class);


        return query.setParameter("login", login).getSingleResult();
    }

    @Override
    public Set<Order> getAllUserOrders(Users user) {
        return user.getOrders();
    }

    @Transactional
    @Override
    public void increaseBalance(Users user, Double addend) {
        user.setBalance(user.getBalance()+addend);
        em.merge(user);
    }

    @Override
    public Double showUserBalance(Users user) {
        return user.getBalance();
    }


}
