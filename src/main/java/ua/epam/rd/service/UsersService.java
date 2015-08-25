package ua.epam.rd.service;

import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Users;

import java.util.Set;

/**
 * Created by alex on 8/25/15.
 */
public interface UsersService {
    public Set<Order> getAllUserOrders(Users user);
    public Users getUserByLogin(String login);
    public void increaseCustomerBalance(Users user, Double addend);
    public Double getUserBalance(Users user);
}
