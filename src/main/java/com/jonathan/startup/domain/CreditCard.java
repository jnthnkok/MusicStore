/**
 *
 * @author boniface
 */
package com.jonathan.startup.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class CreditCard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String creditNumber;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expiryDate;
    
    private String nameOnCreditCard;
    
    private BigDecimal balance;
  
    
    private CreditCard(Builder builder) {
        id = builder.id;
        creditNumber = builder.creditNumber;
        expiryDate = builder.expiryDate;
        nameOnCreditCard = builder.nameOnCreditCard;
        balance = builder.balance; 
    }

    private CreditCard() {
    }

    public static class Builder {

        private Long id;
        private String creditNumber;
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date expiryDate;
        private String nameOnCreditCard;
        private BigDecimal balance; 

        public Builder(String creditNumber) {
            this.creditNumber = creditNumber;
        }

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder expiryDate(Date value) {
            this.expiryDate = value;
            return this;
        }

        public Builder nameOnCreditCard(String value) {
            this.nameOnCreditCard = value;
            return this;
        }

        public Builder balance(BigDecimal value) {
            this.balance = value;
            return this;
        }
        
        public Builder CreditCard(CreditCard value) {
            id = value.getId();
            creditNumber = value.getCreditNumber();
            expiryDate = value.getExpiryDate();
            nameOnCreditCard = value.getNameOnCreditCard();
            balance = value.getBalance();  
            return this;
        }

        public CreditCard build() {
            return new CreditCard(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getNameOnCreditCard() {
        return nameOnCreditCard;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final CreditCard other = (CreditCard) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreditCard{" + "creditNumber=" + creditNumber + '}';
    }
}
