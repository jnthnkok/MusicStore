package com.jonathan.startup.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SampleClip {
    private String sampleURL;

    private SampleClip() {
    }

    private SampleClip(Builder build) {
        sampleURL = build.sampleURL;
    }
    
    public static class Builder{
        
        private String sampleURL;
        
        public Builder(String sampleURL){
            this.sampleURL = sampleURL;
        }
        
        public Builder SampleClip(SampleClip sampleClip){
            sampleURL = sampleClip.getSampleURL();
            return this;
        }
        
        public SampleClip build(){
            return new SampleClip(this);
        }
    }

    public String getSampleURL() {
        return sampleURL;
    }

    @Override
    public String toString() {
        return "SampleClip{" + "sampleURL=" + sampleURL + '}';
    }
}

