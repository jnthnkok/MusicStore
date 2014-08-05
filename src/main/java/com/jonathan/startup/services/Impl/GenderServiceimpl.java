/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.startup.services.Impl;

import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.Customer;
import com.jonathan.startup.domain.OrderItem;
import com.jonathan.startup.repository.AlbumRepository;
import com.jonathan.startup.repository.CustomerRepository;
import com.jonathan.startup.services.GenderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Kok
 */
@Service
public class GenderServiceimpl implements GenderService{

    @Autowired
    private AlbumRepository albumRepo;
    @Autowired
    private CustomerRepository custRepo;
    
    @Override
    public ArrayList<Album> getAlbumGender(String gender) {
       ArrayList<Album> allAlbums = new ArrayList<Album>();
       ArrayList<Customer> customers = new ArrayList<Customer>();
       ArrayList<Album> genderAlbums = new ArrayList<Album>();
       
       customers = (ArrayList<Customer>) custRepo.findAll();
       allAlbums = (ArrayList<Album>)findAll();
       for(Customer cust: customers)
         if(cust.getDemographic().getGender().equalsIgnoreCase(gender))
             for(int i = 0; i < cust.getOrder().size(); i++)
                 for(OrderItem o: cust.getOrder().get(i).getItems())
                     genderAlbums.add(o.getAlbum());

       return allAlbums;
    
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
    public Album merge(Album entity){
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
