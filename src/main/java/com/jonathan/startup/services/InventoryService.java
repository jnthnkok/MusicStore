package com.jonathan.startup.services;

import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.Orders;


/**
 *
 * @author Jonathan Kok
 */
public interface InventoryService extends Services<Album, Long>{
    public Album processPurchase(Album a, Orders o);
}
