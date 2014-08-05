package com.jonathan.startup.test.repository;

import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.AlbumCategory;
import com.jonathan.startup.domain.ArtistInformation;
import com.jonathan.startup.domain.MarketingInformation;
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
import com.jonathan.startup.test.ConnectionConfigTest;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class AlbumRepositoryTest {
    public static ApplicationContext ctx;
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
    
    public AlbumRepositoryTest() {
    }

    @Test
     public void createAlbum(){
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
              .sales(90)
              .track(epTracks)
              .unitPrice(new BigDecimal(100.00))
              .category(albumCat)
              .build();
      
     albumRepository.save(album);
     
     id = album.getId();
     Assert.assertNotNull(id);   
    }
     
    @Test(dependsOnMethods = "createAlbum")
    public void readAlbum(){
    albumRepository = ctx.getBean(AlbumRepository.class);
    Album album = albumRepository.findOne(id);
    Assert.assertEquals(album.getAlbumTitle(), "The Long Way Home");
    }
     
    @Test(dependsOnMethods = "readAlbum")
    public void updateAlbum(){
    albumRepository = ctx.getBean(AlbumRepository.class);
    Album album = albumRepository.findOne(id);

    Album updatedAlbum = new Album.Builder("The Long Way Home")
            .Album(album)
            .inventory(300)
            .build();
    
    albumRepository.save(updatedAlbum);
    
     Album newAlbum = albumRepository.findOne(id); 
    Assert.assertEquals(newAlbum.getInventory(), 300);
    }
    
    @Test(dependsOnMethods = "updateAlbum")
    public void deleteAlbum(){
    albumRepository = ctx.getBean(AlbumRepository.class);
    Album album = albumRepository.findOne(id);
    albumRepository.delete(album);
    
    Album deletedAlbum = albumRepository.findOne(id);

    Assert.assertNull(deletedAlbum);
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
