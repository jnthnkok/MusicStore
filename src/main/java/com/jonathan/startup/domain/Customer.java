/**
 *
 * @author Jonathan Kok
 */
package com.jonathan.startup.domain;
import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    private String customerNumber;
    
    @Embedded    
    private Name name;
    
    @Embedded
    private Demographic demographic;
    
    @Embedded
    private Contact contact;
    
    @OneToOne
    private CustomerAddress customerAddress;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private List<CreditCard> creditCard; 
    
    @OneToMany(fetch = FetchType.EAGER)  
    @JoinColumn(name = "CUSTOMER_ID")
    private List<Orders> order;
            
    private Customer(){
    }
    
    private Customer(Builder build){
        id = build.id;
        customerNumber = build.customerNumber;
        name = build.name;
        demographic = build.demographic;
        contact = build.contact;
        customerAddress = build.customerAddress;
        order = build.order;
        creditCard = build.creditCard;
    }
    
    public static class Builder{
    private Long id; 
    private String customerNumber;
    private Name name;
    private Demographic demographic;
    private Contact contact;
    private CustomerAddress customerAddress;
    private List<CreditCard> creditCard = new ArrayList<>(); 
    private List<Orders> order = new ArrayList<>();
    
    public Builder(String customerNumber){
        this.customerNumber = customerNumber;
    }
    
    public Builder id(Long id){
        this.id = id;
        return this;
    }
    
    public Builder name(Name name){
        this.name = name;
        return this;
    }
    
    public Builder demographic(Demographic demographic){
        this.demographic = demographic;
        return this;
    }
    
    public Builder contact(Contact contact){
        this.contact = contact;
        return this;
    }
    
    public Builder customerAddress(CustomerAddress customerAddress){
        this.customerAddress = customerAddress;
        return this;
    }
    
    public Builder creditCard(List<CreditCard> creditCard){
        this.creditCard = creditCard;
        return this;
    }
    
    public Builder order(List<Orders> order){
        this.order = order;
        return this;
    }
        
    public Builder customer(Customer customer){
        id = customer.getId();
        customerNumber = customer.getCustomerNumber();
        name = customer.getName();
        demographic = customer.getDemographic();
        contact = customer.getContact();
        customerAddress = customer.getCustomerAddress();
        order = customer.getOrder();
        creditCard = customer.getCreditCard();
        return this;
    }
    
    public Customer build(){
        return new Customer(this);
    }
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Demographic getDemographic() {
        return demographic;
    }

    public void setDemographic(Demographic demographic) {
        this.demographic = demographic;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<CreditCard> getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(List<CreditCard> creditCard) {
        this.creditCard = creditCard;
    }

    public List<Orders> getOrder() {
        return order;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", customerNumber=" + customerNumber + ", name=" + name + ", demographic=" + demographic + ", contact=" + contact + ", customerAddress=" + customerAddress + ", creditCard=" + creditCard + ", order=" + order + '}';
    }
}