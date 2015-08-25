package ua.epam.rd.repository;

import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Users;

import java.util.Set;

/**
 * Created by alex on 8/25/15.
 */
public interface UsersRepository {
    public Users getUserByLogin(String login);
    public Set<Order> getAllUserOrders(Users user);
}
