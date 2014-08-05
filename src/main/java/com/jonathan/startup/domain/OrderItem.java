package com.jonathan.startup.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    
    @OneToOne
    private Album album;
    
    private OrderItem(){
    }

    private OrderItem(Builder build) {
        id = build.id;
        quantity = build.quantity; 
        album = build.album;
    }
    
    public static class Builder{
        
         private Long id;
         private int quantity; 
         private Album album;
         
         public Builder(int quantity){
             this.quantity = quantity;
         }
         
         public Builder id(Long id){
             this.id = id;
             return this;
         }
          
         
         public Builder album(Album album){
             this.album = album;
             return this;
         }
         
         public Builder OrderItem(OrderItem orderItem){
             id = orderItem.getId();
             quantity = orderItem.getQuantity();
             album = orderItem.getAlbum();
             return this;
         }
         
         public OrderItem build(){
             return new OrderItem(this);
         }
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
 

    public Album getAlbum() {
        return album;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final OrderItem other = (OrderItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.album, other.album)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", quantity=" + quantity + ", album=" + album + '}';
    } 
}

