package com.jonathan.startup.domain;

import com.jonathan.startup.domain.CustomerInvoice;
import com.jonathan.startup.domain.OrderItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int orderNumber;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;
 
    @OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name = "ORDER_ID")
    private List<OrderItem> items;
    
    @OneToOne 
    private CustomerInvoice invoice;
    
    private Orders(){     
    }

    private Orders(Builder build) {
        id = build.id;
        orderDate = build.orderDate;
        orderNumber = build.orderNumber; 
        invoice = build.invoice;
        items = build.items;
    }
    public static class Builder {

        private int orderNumber;
        private Date orderDate;
        private Long id; 
        private CustomerInvoice invoice;
        private List<OrderItem> items = new ArrayList<>();
        
        public Builder(int order){
            this.orderNumber = order;
        }
        
        public Builder id(Long id){
            this.id = id;
            return this;
        }
        
        public Builder orderDate(Date d){
            this.orderDate = d;
            return this;
        }
         
        public Builder invoice(CustomerInvoice inv){
            this.invoice = inv;
            return this;
        }
        
        public Builder orderItem(List<OrderItem> ite){
            this.items = ite;
            return this;
        }
        
        public Builder Order(Orders ord){
            id = ord.getId();
            orderDate = ord.getOrderDate();
            orderNumber = ord.getOrderNumber(); 
            invoice = ord.getInvoice();
            items = ord.getItems();
            
            return this;
        }
        
        public Orders build(){
            return new Orders(this);
        }
        
    }

    public Long getId() {
        return id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }
 

    public CustomerInvoice getInvoice() {
        return invoice;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orders other = (Orders) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        if (!Objects.equals(this.invoice, other.invoice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orders{" + "id=" + id + ", orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", item=" + items + ", invoice=" + invoice + '}';
    }
}
