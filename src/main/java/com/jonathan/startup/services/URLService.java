package com.jonathan.startup.services;
import com.jonathan.startup.domain.Album;
/**
 *
 * @author Jonathan Kok
 */
public interface URLService extends Services<Album, Long>{
    public String getTrackURL(Long trackID);
}
