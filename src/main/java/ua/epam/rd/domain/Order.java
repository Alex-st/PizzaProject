/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.rd.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by alex on 8/14/15.
 */
@Entity
@Table(name = "Orders")
@Component("order")
@Scope(value = "prototype")
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ORDER_ITEMS",
            joinColumns = @JoinColumn(name = "ORDER_ID"))
    @MapKeyJoinColumn(name = "PIZZA_ID", referencedColumnName = "ID")
    @Column(name = "ITEMS")
    private Map<Pizza, Integer> orderItems = new HashMap<>();
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users customer;


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Map<Pizza, Integer> getOrderItems() {
        return orderItems;
    }    

    public void setOrderItems(Map<Pizza, Integer> items) {
        this.orderItems = items;
    }

    public Order() {
        this.date = new Date();
    }

    public Order(int newOrdeId, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addItems(Pizza... items) {
        for (Pizza item : items) {
            Integer count = this.orderItems.get(item);
            count = (count == null ? 1 : count + 1);           
            this.orderItems.put(item, count);
        }
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }

    public void addItems(List<Pizza> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", date=" + date + "}";
    }

    
    
}
