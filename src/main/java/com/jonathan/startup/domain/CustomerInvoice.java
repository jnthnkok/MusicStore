package com.jonathan.startup.domain;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

@Entity
public class CustomerInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoiceDate;
    
    private BigDecimal orderAmount;
    
    private String invoiceStatus;
   
    private CustomerInvoice(){
        
    }

    private CustomerInvoice(Builder build) {
        id = build.id;
        invoiceDate = build.invoiceDate;
        invoiceStatus = build.invoiceStatus;
        orderAmount = build.orderAmount; 
    }
    public static class Builder {

        private Long id;
        private Date invoiceDate;
        private BigDecimal orderAmount;
        private String invoiceStatus; 

        public Builder(BigDecimal orderAmount){
            this.orderAmount = orderAmount;
        }
        
        public Builder invoiceDate(Date invoiceDate){
            this.invoiceDate = invoiceDate;
            return this;
        }
        
        public Builder invoiceStatus(String invoiceStatus){
            this.invoiceStatus = invoiceStatus;
            return this;
        }
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        } 
        
        public Builder  CustomerInvoice(CustomerInvoice custInvoice){
            id = custInvoice.getId();
            invoiceDate = custInvoice.getInvoiceDate();
            invoiceStatus = custInvoice.getInvoiceStatus();
            orderAmount = custInvoice.getOrderAmount(); 
            
            return this;
        }
        
        public CustomerInvoice build(){
            return new CustomerInvoice(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public String toString() {
        return "CustomerInvoice{" + "id=" + id + ", invoiceDate=" + invoiceDate + ", orderAmount=" + orderAmount + ", invoiceStatus=" + invoiceStatus + '}';
    }
}

