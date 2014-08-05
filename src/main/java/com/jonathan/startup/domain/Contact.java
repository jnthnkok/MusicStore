/**
 *
 * @author Jonathan Kok
 */
package com.jonathan.startup.domain;
import java.io.*;
import javax.persistence.*;

@Embeddable
public class Contact implements Serializable {
    private String phoneNumber;
    
    private String cellNumber;

    private Contact(){
    }
    
    private Contact(Builder build) {
        phoneNumber = build.phoneNumber;
        cellNumber = build.cellNumber;
    }

    public static class Builder {
        private String phoneNumber;
        private String cellNumber;
        
        public Builder(String phoneNumber){
            this.phoneNumber = phoneNumber;
        }
        
        public Builder cellNumber(String cellNumber){
            this.cellNumber = cellNumber;
            return this;
        }
        
        public Builder Contact(Contact contact){
            phoneNumber = contact.getPhoneNumber();
            cellNumber = contact.getCellNumber();
            return this;
        }
        
        public Contact build(){
            return new Contact(this);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    @Override
    public String toString() {
        return "Contact{" + "phoneNumber=" + phoneNumber + ", cellNumber=" + cellNumber + '}';
    }  
}
