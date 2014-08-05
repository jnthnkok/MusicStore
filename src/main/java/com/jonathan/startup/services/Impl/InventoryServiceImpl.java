package com.jonathan.startup.services.Impl;

import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.AlbumCategory;
import com.jonathan.startup.domain.ArtistInformation;
import com.jonathan.startup.domain.CustomerInvoice;
import com.jonathan.startup.domain.MarketingInformation;
import com.jonathan.startup.domain.OrderItem;
import com.jonathan.startup.domain.Orders;
import com.jonathan.startup.domain.Review;
import com.jonathan.startup.domain.SampleClip;
import com.jonathan.startup.domain.Track;
import com.jonathan.startup.repository.AlbumCategoryRepository;
import com.jonathan.startup.repository.AlbumRepository;
import com.jonathan.startup.repository.ArtistInformationRepository;
import com.jonathan.startup.repository.CustomerInvoiceRepository;
import com.jonathan.startup.repository.MarketingInformationRepository;
import com.jonathan.startup.repository.OrderItemRepository;
import com.jonathan.startup.repository.OrderRepository;
import com.jonathan.startup.repository.ReviewRepository;
import com.jonathan.startup.repository.TrackRepository;
import com.jonathan.startup.services.InventoryService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jonathan Kok
 */
@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private AlbumRepository albumRepo;
    public static ApplicationContext ctx;
    private InventoryService service;
    private OrderRepository orderRepository;
    private AlbumCategoryRepository albumCategoryRepository;
    private OrderItemRepository orderItemRepository;
    private MarketingInformationRepository marketingInformationRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private ArtistInformationRepository artistInformationRepository;
    private ReviewRepository reviewRepository;
    private CustomerInvoiceRepository customerInvoiceRepository;
    private Long id;
    @Override
    public Album processPurchase(Album a, Orders o)  {
/*
         
      //Track
      SampleClip sampleClip = new SampleClip.Builder("www.youtube.com").build();
      ArrayList<Track> epTracks = new ArrayList<Track>();
      epTracks.add(new Track.Builder(1).title("Interlude").sampleClip(sampleClip).build());
      epTracks.add(new Track.Builder(2).title("Reading").sampleClip(sampleClip).build());
      epTracks.add(new Track.Builder(3).title("The Long Way Home").sampleClip(sampleClip).build());        
      epTracks.add(new Track.Builder(4).title("Exitlude").sampleClip(sampleClip).build());
      trackRepository.save(epTracks);
     
      //Category
      AlbumCategory albumCat = new AlbumCategory.Builder("Rock").build();
      albumCategoryRepository.save(albumCat);
      
      ArtistInformation artInfo = new ArtistInformation.Builder("Gretta").lastname("Snow").build();
      artistInformationRepository.save(artInfo);
      
      ArrayList<Review> reviews = new ArrayList<Review>();
      reviews.add(new Review.Builder(10).description("Exceptional!").build());
      reviewRepository.save(reviews);
      

      MarketingInformation marketInfo = new MarketingInformation.Builder("Approved").artistInfo(artInfo).review(reviews).build();
      marketingInformationRepository.save(marketInfo);
              
      Album album = new Album.Builder("The Long Way Home")
              .inventory(100)
              .marketingInfo(marketInfo)
              .sales(0)
              .track(epTracks)
              .unitPrice(new BigDecimal(100.00))
              .category(albumCat)
              .build();
      
     albumRepository.save(album);
     
     
      ArrayList<OrderItem> items = new ArrayList<OrderItem>();
      items.add(new OrderItem.Builder(1).album(album).build());
      orderItemRepository.save(items);
      
      CustomerInvoice invoice = new CustomerInvoice.Builder(new BigDecimal(100.00)).invoiceDate(new Date()).invoiceStatus("UNPAID").build();
      customerInvoiceRepository.save(invoice);
      
      o = new Orders.Builder(1001).orderDate(new Date()).invoice(invoice).orderItem(items).build();
      orderRepository.save(o);
      
      
       a = new Album.Builder("The Long Way Home")
            .Album(album)
            .inventory(album.getInventory() - o.getItems().size())
            .build();
    
         albumRepository.save(a);
      */
         return a;
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
