package com.jonathan.startup.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name implements Serializable{
    private String firstName;
    private String lastName;
    
    private Name(){
    }

    private Name(Builder build) {
        firstName = build.firstName;
        lastName = build.lastName;
    }
    
    public static class Builder{
        private String firstName;
        private String lastName;
        
        public Builder(String firstName){
            this.firstName = firstName;
        }
        
        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        
        public Builder Name(Name name){
            this.firstName = name.getFirstName();
            this.lastName = name.getLastName();
            return this;
        }
        
        public Name build(){
            return new Name(this);
        }
        
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Name{" + "firstName=" + firstName + ", lastName=" + lastName + '}';
    }
}
