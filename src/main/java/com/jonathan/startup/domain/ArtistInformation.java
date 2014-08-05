/**
 *
 * @author Jonathan Kok
 */
package com.jonathan.startup.domain;
import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class ArtistInformation implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private ArtistInformation() {
    }

    private ArtistInformation(Builder build) {
        id = build.id;
        firstName = build.firstName;
        lastName = build.lastName;
    }
    
    public static class Builder{
        
        private Long id;
        private String firstName;
        private String lastName;
        
        public Builder(String firstName){
            this.firstName = firstName;
        }
        
        public Builder id(Long id){
            id = id;
            return this;
        }
        
        public Builder lastname(String lastName){
            this.lastName = lastName;
            return this;
        }
        
        public Builder ArtistInformation(ArtistInformation artistInfo){
            id = artistInfo.getId();
            firstName = artistInfo.getFirstName();
            lastName = artistInfo.getLastName();      
            return this;
        }
        
        public ArtistInformation build(){
            return new ArtistInformation(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
        final ArtistInformation other = (ArtistInformation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArtistInformation{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }  
}