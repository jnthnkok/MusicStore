package com.jonathan.startup.services.Impl;

import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.Track;
import com.jonathan.startup.repository.AlbumRepository;
import com.jonathan.startup.services.URLService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Kok
 */
@Service
public class URLServiceImpl implements URLService{
    @Autowired 
    private AlbumRepository albumRepo;
    
    @Override
    public String getTrackURL(Long trackID) {
      ArrayList<Album> allAlbums = new ArrayList<Album>();
      allAlbums = (ArrayList<Album>)findAll();
      for(Album a: allAlbums)
     
          
      for(int i = 0; i < allAlbums.size(); i++) {
          for(int j = 0; j < allAlbums.get(i).getTrack().size(); j++) {
              if(allAlbums.get(i).getTrack().get(j).getId().equals(trackID))
                  return allAlbums.get(i).getTrack().get(j).getClip().getSampleURL();
          }
      
      }
          
         return null; 
    }

    @Override
    public Album find(Long id) {
        return albumRepo.findOne(id);
    }

    @Override
    public Album persist(Album entity) {
        return albumRepo.save(entity);
    }

    @Override
    public Album merge(Album entity) {
        return albumRepo.save(entity);
    }

    @Override
    public void remove(Album entity) {
        albumRepo.delete(entity);
    }

    @Override
    public List<Album> findAll() {
        return albumRepo.findAll();
    } 
}
