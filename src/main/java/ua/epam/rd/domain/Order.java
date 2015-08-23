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
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author andrii
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

    @ElementCollection
    @CollectionTable(name = "ORDER_ITEMS",
            joinColumns = @JoinColumn(name = "ORDER_ID"))
    @MapKeyJoinColumn(name = "PIZZA_ID", referencedColumnName = "ID")
    @Column(name = "ITEMS")
    private Map<Pizza, Integer> items = new HashMap<>();
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

//    @Version
//    private Long version;

    public Long getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Map<Pizza, Integer> getItems() {
        return items;
    }    

    public void setItems(Map<Pizza, Integer> items) {
        this.items = items;
    }

    public Order() {
        this.date = new Date();
    }

    public Order(int newOrdeId, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addItems(Pizza... items) {
        for (Pizza item : items) {
            Integer count = this.items.get(item);            
            count = (count == null ? 1 : count + 1);           
            this.items.put(item, count);
        }
    }
    
    public void addItems(List<Pizza> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", date=" + date + "}";
    }

    
    
}
