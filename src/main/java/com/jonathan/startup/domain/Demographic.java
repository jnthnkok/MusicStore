package com.jonathan.startup.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Demographic implements Serializable {
    private String gender;
    private String race;
    private Date dateOfBirth;

    private Demographic(){
        
    }
    private Demographic(Builder build) {
        gender = build.gender;
        race = build.race;
        dateOfBirth = build.dateOfBirth;
    }

    public static class Builder {

        private String gender;
        private String race;
        private Date dateOfBirth;
        
        public Builder(String gender){
            this.gender = gender;
        }
        
        public Builder race(String race){
            this.race = race;
            return this;
        }
        
        public Builder birth(Date dateOfBirth){
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        
        public Builder Demographic(Demographic demographic){
            this.dateOfBirth = demographic.getDateOfBirth();
            this.gender = demographic.getGender();
            this.race = demographic.getRace();
            return this;
        }
        
        public Demographic build(){
            return new Demographic(this);
        }
    }

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Demographic{" + "gender=" + gender + ", race=" + race + ", dateOfBirth=" + dateOfBirth + '}';
    }
}
