/**
 *
 * @author Jonathan Kok
 */
package com.jonathan.startup.domain;
import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class AlbumCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String categoryName;
     
    private AlbumCategory() {
    }

    private AlbumCategory(Builder build) {
        id = build.id;
        categoryName = build.categoryName; 
    }

    public static class Builder {
        private Long id;
        private String categoryName; 
        
        public Builder(String categoryName){
            this.categoryName = categoryName;
        }
        
        public Builder id(Long id){
            this.id = id;
            return this;
        }
               
        public Builder categoryName(String categoryName){
            this.categoryName = categoryName;
            return this;
        }
        
        public Builder albumCategory(AlbumCategory albumCategory){
            id = albumCategory.getId();
            categoryName = albumCategory.getCategoryName(); 
            return this;
        }
        
        public AlbumCategory build(){
            return new AlbumCategory(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
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
        final AlbumCategory other = (AlbumCategory) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        return true;
    } 
    
    @Override
    public String toString() {
        return "AlbumCategory{" + "id=" + id + ", categoryName=" + categoryName + '}';
    }   
}

