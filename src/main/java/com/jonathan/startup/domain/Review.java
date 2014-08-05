package com.jonathan.startup.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private int rating;

    private Review(){
    }
    
    private Review(Builder build) {
        id = build.id;
        description = build.description;
        rating = build.rating;
    }

    public static class Builder {
        private String description;
        private int rating;
        private Long id;
        
        public Builder(int rating){
            this.rating = rating;
            
        }
        
        public Builder id(Long id){
            this.id = id;
            return this;
        }
        
        public Builder description(String description){
            this.description = description;
            return this;
        }
        
        public Builder Review(Review review){
            id = review.getId();
            description = review.getDescription();
            rating = review.getRating();
            return this;
        }
        
        public Review build(){
            return new Review(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Review other = (Review) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.rating != other.rating) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", description=" + description + ", rating=" + rating + '}';
    }   
}
