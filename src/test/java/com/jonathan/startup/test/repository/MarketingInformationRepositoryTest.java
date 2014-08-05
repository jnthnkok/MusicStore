package com.jonathan.startup.test.repository;

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
import com.jonathan.startup.test.ConnectionConfigTest;
import static com.jonathan.startup.test.repository.OrderItemRepositoryTest.ctx;
import static com.jonathan.startup.test.repository.OrderRepositoryTest.ctx;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.criteria.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;;

/**
 *
 * @author Jonathan Kok
 */
public class MarketingInformationRepositoryTest {
    public static ApplicationContext ctx;
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;
    private AlbumCategoryRepository albumCategoryRepository;
    private MarketingInformationRepository marketingInformationRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private ArtistInformationRepository artistInformationRepository;
    private ReviewRepository reviewRepository;
    private CustomerInvoiceRepository customerInvoiceRepository; 
    private Long id;
    

    public MarketingInformationRepositoryTest() {
    }


    @Test
    public void createMarketingInformation(){
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
      ArrayList<Track> tracks = new ArrayList<Track>();
      tracks.add(new Track.Builder(1).title("Intro").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(2).title("Drama").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(3).title("Stars").sampleClip(sampleClip).build());        
      tracks.add(new Track.Builder(4).title("Arrow").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(5).title("People").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(6).title("Meaning").sampleClip(sampleClip).build());
      
      trackRepository.save(tracks);
     
      //Category
      AlbumCategory albumCat = new AlbumCategory.Builder("Country").build();
      albumCategoryRepository.save(albumCat);
      
      ArtistInformation artInfo = new ArtistInformation.Builder("Lisa").lastname("Hudrey").build();
      artistInformationRepository.save(artInfo);
      
      ArrayList<Review> reviews = new ArrayList<Review>();
      reviews.add(new Review.Builder(7).description("Good").build());
      reviewRepository.save(reviews);
     
      MarketingInformation marketInfo = new MarketingInformation.Builder("Approved").artistInfo(artInfo).review(reviews).build();
      marketingInformationRepository.save(marketInfo); 
      
      id = marketInfo.getId();
      
      Assert.assertNotNull(id);
    }

    @Test(dependsOnMethods = "createMarketingInformation")
    public void readMarketingInformation(){
       marketingInformationRepository = ctx.getBean(MarketingInformationRepository.class);
       MarketingInformation marketInfo = marketingInformationRepository.findOne(id);
       Assert.assertEquals(marketInfo.getArtistInformation().getFirstName(), "Lisa");
    }
    
    @Test(dependsOnMethods = "readMarketingInformation")
    public void updateMarketingInformation(){
       marketingInformationRepository = ctx.getBean(MarketingInformationRepository.class);
       MarketingInformation marketInfo = marketingInformationRepository.findOne(id);

      ArrayList<Review> review2 = new ArrayList<Review>();
      review2.add(new Review.Builder(10).description("Awesome").build());
      reviewRepository.save(review2);
      
      
       MarketingInformation updatedMarketInfo = new MarketingInformation.Builder("Not Approved")
               .MarketingInformation(marketInfo)
               .review(review2)
               .build();
       
       marketingInformationRepository.save(updatedMarketInfo);  
       
       MarketingInformation newMarketInfo = marketingInformationRepository.findOne(id);
       
       Assert.assertEquals(newMarketInfo.getArtistInformation().getFirstName(), "Lisa");
    }
    
    @Test(dependsOnMethods = "updateMarketingInformation")
    public void deleteMarketingInformation(){
       marketingInformationRepository = ctx.getBean(MarketingInformationRepository.class);
       MarketingInformation marketInfo = marketingInformationRepository.findOne(id);
       marketingInformationRepository.delete(marketInfo);
       
       MarketingInformation deletedMarketInfo = marketingInformationRepository.findOne(id);
       Assert.assertNull(deletedMarketInfo);
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
