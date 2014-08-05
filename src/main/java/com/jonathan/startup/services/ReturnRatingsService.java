package com.jonathan.startup.services;

import com.jonathan.startup.domain.Album;
import java.util.ArrayList;

/**
 *
 * @author Jonathan Kok
 */
public interface ReturnRatingsService extends Services<Album, Long>{
    public ArrayList getRatings(String title);
}
