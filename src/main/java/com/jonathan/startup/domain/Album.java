/**
 *
 * @author Jonathan Kok
 */
package com.jonathan.startup.domain;

import java.math.*;
import java.io.*;
import java.util.*;
import javax.persistence.*;

@Entity
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Album Builder;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String albumTitle;
    
    private BigDecimal unitPrice;
    private int inventory;
    private int sales;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Track> track;

    @ManyToOne
    private AlbumCategory albumCategory;

    @OneToOne
    private MarketingInformation marketingInfo;
    
    private Album() {
    }

    private Album(Builder build) {
        id = build.id;
        albumTitle = build.albumTitle;
        unitPrice = build.unitPrice;
        inventory = build.inventory;
        sales = build.sales;
        track = build.track;
        albumCategory = build.albumCategory;
        marketingInfo = build.marketingInfo;
    }

    public static class Builder {
        private String albumTitle;
        private BigDecimal unitPrice;
        private int inventory;
        private int sales;
        private Long id;
        private List<Track> track = new ArrayList<>();
        private AlbumCategory albumCategory;
        private MarketingInformation marketingInfo;

        public Builder(String title){
            this.albumTitle = title;
        }
        
        public Builder unitPrice(BigDecimal unitPrice){
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder inventory(int inventory){
            this.inventory = inventory;
            return this;
        }

        public Builder sales(int sales){
            this.sales = sales;
            return this;
        }

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder track(List<Track> track){
            this.track = track;
            return this;
        }
        
        public Builder marketingInfo(MarketingInformation marketingInfo){
            this.marketingInfo = marketingInfo;
            return this;
        }

        public Builder category(AlbumCategory albumCategory) {
            this.albumCategory = albumCategory;
            return this;
        }

        public Builder Album(Album album) {
            albumTitle = album.getAlbumTitle();
            unitPrice = album.getUnitPrice();
            inventory = album.getInventory();
            sales = album.getSales();
            id = album.getId();
            track = album.getTrack();
            albumCategory = album.getCategory();
            marketingInfo = album.getMarketingInformation();
            return this;
        }

        public Album build() {
            return new Album(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getInventory() {
        return inventory;
    }

    public int getSales() {
        return sales;
    }

    public List<Track> getTrack() {
        return track;
    }

    public AlbumCategory getCategory() {
        return albumCategory;
    }

    public MarketingInformation getMarketingInformation() {
        return marketingInfo;
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
        final Album other = (Album) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.albumTitle, other.albumTitle)) {
            return false;
        }
        if (!Objects.equals(this.unitPrice, other.unitPrice)) {
            return false;
        }
        if (this.inventory != other.inventory) {
            return false;
        }
        if (this.sales != other.sales) {
            return false;
        }
        if (!Objects.equals(this.track, other.track)) {
            return false;
        }
        if (!Objects.equals(this.albumCategory, other.albumCategory)) {
            return false;
        }
        if (!Objects.equals(this.marketingInfo, other.marketingInfo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", albumTitle=" + albumTitle + ", unitPrice=" + unitPrice + ", inventory=" + inventory + ", sales=" + sales + ", track=" + track + ", albumCategory=" + albumCategory + ", marketingInfo=" + marketingInfo + '}';
    }
}
