package com.jonathan.startup.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class MarketingInformation implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String statusOfInformation;

    @OneToOne
    private ArtistInformation artistInformation;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn( name = "marketinginformation_id")
    private List<Review> review;
    
    private MarketingInformation(){
        
    }

    private MarketingInformation(Builder build) {
        id = build.id;
        statusOfInformation = build.statusOfInformation;
        review = build.review;
        artistInformation = build.artistInformation;
    }
    public static class Builder {

        private Long id;
        private String statusOfInformation; 
        private List<Review> review;
        private ArtistInformation artistInformation;
        
        public Builder(String statusOfInformation){
            this.statusOfInformation = statusOfInformation; 
        }
        
        public Builder id(long id){
            this.id = id;
            return this;
        }
        
         public Builder artistInfo(ArtistInformation artistInformation){
            this.artistInformation = artistInformation;
            return this;
        }
        
        public Builder review(List<Review> review){
            review = review;
            return this;
        }
        
        public Builder MarketingInformation(MarketingInformation info){
            id = info.getId();
            statusOfInformation = info.getStatusOfInformation(); 
            artistInformation = info.getArtistInformation();
            review = info.getReview();
            return this;
        }
        
        public MarketingInformation build(){
            return new MarketingInformation(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getStatusOfInformation() {
        return statusOfInformation;
    }
 
    public ArtistInformation getArtistInformation() {
        return artistInformation;
    }

    public List<Review> getReview() {
        return review;
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
        final MarketingInformation other = (MarketingInformation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.statusOfInformation, other.statusOfInformation)) {
            return false;
        }
        if (!Objects.equals(this.artistInformation, other.artistInformation)) {
            return false;
        }
        if (!Objects.equals(this.review, other.review)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MarketingInformation{" + "id=" + id + ", statusOfInformation=" + statusOfInformation + ", artistInformation=" + artistInformation + ", review=" + review + '}';
    }
    
    
    
    
}

