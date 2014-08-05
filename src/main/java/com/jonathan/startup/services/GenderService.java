package com.jonathan.startup.services;

import com.jonathan.startup.domain.Album;
import java.util.ArrayList;
/**
 *
 * @author Jonathan Kok
 */
public interface GenderService extends Services<Album, Long>{
    public ArrayList<Album> getAlbumGender(String gender);
}
