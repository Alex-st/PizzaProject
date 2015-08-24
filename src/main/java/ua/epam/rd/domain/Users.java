package ua.epam.rd.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by alex on 8/24/15.
 */

@Entity
public class Users {

        @Id
        @GeneratedValue
        private Long userId;

        private String login;

        private String pass;

        private String name;

        private Double balance;

        @Enumerated(EnumType.STRING)
        private Roles roles;

        @OneToMany(mappedBy = "customer")
        private Set<Order> orders;


    public Users() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String password) {
        this.pass = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
