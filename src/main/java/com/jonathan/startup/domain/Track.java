package com.jonathan.startup.domain;
import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class Track implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    private int trackNumber;

    private String trackTitle;
    
    @Embedded
    private SampleClip sampleClip;
     
    private Track(){  
    }

    private Track(Builder build) {
        id = build.id;
        trackNumber = build.trackNumber;
        trackTitle = build.trackTitle; 
        sampleClip = build.sampleClip;
    }
    
    public static class Builder{
        private Long id; 
        private int trackNumber;
        private String trackTitle; 
        private SampleClip sampleClip;
        
        public Builder(int trackNumber){
            this.trackNumber = trackNumber; 
        }
        
        public Builder id(Long id){
            this.id = id;
            return this;
        }
        
        public Builder title(String trackTitle){
            this.trackTitle = trackTitle;
            return this; 
        }
      
         public Builder sampleClip(SampleClip sampleClip){
            this.sampleClip = sampleClip;
            return this;
        }

        public Builder track(Track track){
            id = track.getId();
            trackNumber = track.getTrackNumber();
            trackTitle = track.getTrackTitle(); 
            sampleClip = track.getClip();
            
            return this;
        }
        
        public Track build(){
            return new Track(this);
        }
    }

    public Long getId() {
        return id;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public String getTrackTitle() {
        return trackTitle;
    }
 
     public SampleClip getClip() {
        return sampleClip;
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
        final Track other = (Track) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.trackNumber != other.trackNumber) {
            return false;
        }
        if (!Objects.equals(this.trackTitle, other.trackTitle)) {
            return false;
        }
        if (!Objects.equals(this.sampleClip, other.sampleClip)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Track{" + "id=" + id + ", trackNumber=" + trackNumber + ", trackTitle=" + trackTitle + ", sampleClip=" + sampleClip + '}';
    }
}

