package com.jonathan.startup.services.Impl;

import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.MarketingInformation;
import com.jonathan.startup.domain.Review;
import com.jonathan.startup.domain.Track;
import com.jonathan.startup.repository.AlbumRepository;
import com.jonathan.startup.repository.MarketingInformationRepository;
import com.jonathan.startup.repository.ReviewRepository;
import com.jonathan.startup.repository.TrackRepository;
import com.jonathan.startup.services.ReturnRatingsService;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Kok
 */
@Service
public class ReturnRatingsServiceImpl implements ReturnRatingsService{
    @Autowired 
    private AlbumRepository albumRepository;
    
    @Override
    public ArrayList getRatings(String title) {
        ArrayList<Album> allAlbums = new ArrayList<Album>();
        ArrayList<String> ratings = new ArrayList<String>();

        allAlbums = (ArrayList<Album>)findAll();
        
        for(int i = 0; i < allAlbums.size(); i++)
            if(allAlbums.get(i).getAlbumTitle().equalsIgnoreCase(title)){
                for(int j = 0; j < allAlbums.get(i).getMarketingInformation().getReview().size(); j++) {
                    ratings.add(allAlbums.get(i).getMarketingInformation().getReview().get(j).toString());
                }
        }
            
        return ratings;
    }

    @Override
    public Album find(Long id) {
        return albumRepository.findOne(id);
    }

    @Override
    public Album persist(Album entity) {
       return albumRepository.save(entity);
    }

    @Override
    public Album merge(Album entity) {
      return albumRepository.save(entity);
    }

    @Override
    public void remove(Album entity) {
       albumRepository.delete(entity);
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
}
