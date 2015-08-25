package ua.epam.rd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.rd.domain.Order;
import ua.epam.rd.domain.Users;
import ua.epam.rd.repository.PizzaRepository;
import ua.epam.rd.repository.UsersRepository;

import java.util.Set;

/**
 * Created by alex on 8/25/15.
 */
@Service
public class SimpleUsersService implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Set<Order> getAllUserOrders(Users user) {
        return usersRepository.getAllUserOrders(user);
    }

    @Override
    public Users getUserByLogin(String login) {
        return usersRepository.getUserByLogin(login);
    }

    @Override
    public void increaseCustomerBalance(Users user, Double addend) {
        usersRepository.increaseBalance(user, addend);
    }

    @Override
    public Double getUserBalance(Users user) {
        return usersRepository.showUserBalance(user);
    }


}
