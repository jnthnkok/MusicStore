package com.jonathan.startup.domain;
import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class CustomerAddress implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    private String streetAddress;

    private String postalAddress;
    
    private CustomerAddress(){ 
    }
    
    private CustomerAddress(Builder build) {
        streetAddress = build.streetAddress;
        postalAddress = build.postalAddress; 
        id = build.id;
    }
    
    public static class Builder {

        private String streetAddress;
        private String postalAddress; 
        private Long id; 
        
        public Builder(String streetAddress){
            this.streetAddress = streetAddress;
        }
        
        public Builder postalAddress(String postalAddress){
            this.postalAddress = postalAddress;
            return this;
        }
        
         public Builder id(Long id){
             this.id = id;
             return this;
         }
         
        public Builder CustomerAddress(CustomerAddress customerAddress){
            streetAddress = customerAddress.getStreetAddress();
            postalAddress = customerAddress.getPostalAddress(); 
            return this;
        }
        
        public CustomerAddress build(){
            return new CustomerAddress(this);
        }
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
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
        final CustomerAddress other = (CustomerAddress) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.streetAddress, other.streetAddress)) {
            return false;
        }
        if (!Objects.equals(this.postalAddress, other.postalAddress)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CustomerAddress{" + "id=" + id + ", streetAddress=" + streetAddress + ", postalAddress=" + postalAddress + '}';
    }  
}
