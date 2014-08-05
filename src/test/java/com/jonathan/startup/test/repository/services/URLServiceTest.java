/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.startup.test.repository.services;

import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.AlbumCategory;
import com.jonathan.startup.domain.ArtistInformation;
import com.jonathan.startup.domain.Contact;
import com.jonathan.startup.domain.Customer;
import com.jonathan.startup.domain.CustomerAddress;
import com.jonathan.startup.domain.MarketingInformation;
import com.jonathan.startup.domain.Name;
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
import com.jonathan.startup.services.ContactService;
import com.jonathan.startup.services.Impl.ReturnRatingsServiceImpl;
import com.jonathan.startup.services.ReturnRatingsService;
import com.jonathan.startup.services.URLService;
import com.jonathan.startup.test.ConnectionConfigTest;
import static com.jonathan.startup.test.repository.AlbumRepositoryTest.ctx;
import static com.jonathan.startup.test.repository.OrderRepositoryTest.ctx;
import static com.jonathan.startup.test.repository.services.ContactServiceTest.ctx;
import static com.jonathan.startup.test.repository.services.ReturnRatingsServiceTest.ctx;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jonathan Kok
 */
public class URLServiceTest {
        public static ApplicationContext ctx;
        public URLService service;
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
    public URLServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void urlTest() {
     service = ctx.getBean(URLService.class);

      orderRepository = ctx.getBean(OrderRepository.class);
      albumCategoryRepository = ctx.getBean(AlbumCategoryRepository.class);
      orderItemRepository = ctx.getBean(OrderItemRepository.class);
      marketingInformationRepository = ctx.getBean(MarketingInformationRepository.class);
      albumRepository = ctx.getBean(AlbumRepository.class);
      trackRepository = ctx.getBean(TrackRepository.class);
      artistInformationRepository = ctx.getBean(ArtistInformationRepository.class);
      reviewRepository = ctx.getBean(ReviewRepository.class);
      customerInvoiceRepository = ctx.getBean(CustomerInvoiceRepository.class);

      //Track
      SampleClip sampleClip = new SampleClip.Builder("www.youtube.com/JohnSnowClearEp").build();
      ArrayList<Track> epTracks = new ArrayList<Track>();
      epTracks.add(new Track.Builder(1).title("Interlude").sampleClip(sampleClip).build());
      epTracks.add(new Track.Builder(2).title("Clear Sky").sampleClip(sampleClip).build());
      epTracks.add(new Track.Builder(3).title("Tremors").sampleClip(sampleClip).build());        
      epTracks.add(new Track.Builder(4).title("Exitlude").sampleClip(sampleClip).build());
      trackRepository.save(epTracks);
     
      //Category
      AlbumCategory albumCat = new AlbumCategory.Builder("Rock").build();
      albumCategoryRepository.save(albumCat);
      
      ArtistInformation artInfo = new ArtistInformation.Builder("John").lastname("Snow").build();
      artistInformationRepository.save(artInfo);
      
      ArrayList<Review> reviews = new ArrayList<Review>();
      reviews.add(new Review.Builder(10).description("Exceptional!").build());
      reviewRepository.save(reviews);
      

      MarketingInformation marketInfo = new MarketingInformation.Builder("Approved").artistInfo(artInfo).review(reviews).build();
      marketingInformationRepository.save(marketInfo);
              
      Album album = new Album.Builder("Clear Sky EP")
              .inventory(100)
              .marketingInfo(marketInfo)
              .sales(90)
              .track(epTracks)
              .unitPrice(new BigDecimal(100.00))
              .category(albumCat)
              .build();
      
     albumRepository.save(album);
     
     Assert.assertEquals(service.getTrackURL(album.getTrack().get(0).getId()), "www.youtube.com/JohnSnowClearEp");

     }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(ConnectionConfigTest.class);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
