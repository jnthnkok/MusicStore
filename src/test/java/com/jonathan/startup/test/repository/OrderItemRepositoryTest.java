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
import org.testng.annotations.Test;
/**
 *
 * @author Jonathan Kok
 */
public class OrderItemRepositoryTest {
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
    
    public OrderItemRepositoryTest() {
    }


    @Test
    public void createOrderItem(){
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
      tracks.add(new Track.Builder(2).title("Desert").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(3).title("Memento").sampleClip(sampleClip).build());        
      tracks.add(new Track.Builder(4).title("Dreams I").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(5).title("Dreams II").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(6).title("Dreams III").sampleClip(sampleClip).build());
      
      trackRepository.save(tracks);
     
      //Category
      AlbumCategory albumCat = new AlbumCategory.Builder("Blues").build();
      albumCategoryRepository.save(albumCat);
      
      ArtistInformation artInfo = new ArtistInformation.Builder("Jesse").lastname("Marks").build();
      artistInformationRepository.save(artInfo);
      
      ArrayList<Review> reviews = new ArrayList<Review>();
      reviews.add(new Review.Builder(10).description("Good").build());
      reviewRepository.save(reviews);
     
      MarketingInformation marketInfo = new MarketingInformation.Builder("Approved").artistInfo(artInfo).review(reviews).build();
      marketingInformationRepository.save(marketInfo);
              
      Album album = new Album.Builder("Dreams")
              .inventory(120)
              .marketingInfo(marketInfo)
              .sales(100)
              .track(tracks)
              .unitPrice(new BigDecimal(200.00))
              .category(albumCat)
              .build();
      
     albumRepository.save(album);
      
     
      OrderItem item = new OrderItem.Builder(1).album(album).build();
      orderItemRepository.save(item);

      id = item.getId();
      Assert.assertNotNull(id);
    }
    
    @Test(dependsOnMethods = "createOrderItem")
    public void readOrderItem(){
       orderItemRepository = ctx.getBean(OrderItemRepository.class);
       OrderItem item = orderItemRepository.findOne(id);
       Assert.assertEquals(item.getAlbum().getAlbumTitle(), "Dreams");
    }
    
    @Test(dependsOnMethods = "readOrderItem")
   public void updateOrderItem(){
       orderItemRepository = ctx.getBean(OrderItemRepository.class);
       OrderItem item = orderItemRepository.findOne(id);
       
             //Track
      SampleClip sampleClip = new SampleClip.Builder("www.youtube.com").build();
      ArrayList<Track> tracks = new ArrayList<Track>();
      tracks.add(new Track.Builder(1).title("Intro").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(2).title("Desert").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(3).title("Memento").sampleClip(sampleClip).build());        
      tracks.add(new Track.Builder(4).title("Dreams I").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(5).title("Dreams II").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(6).title("Dreams III").sampleClip(sampleClip).build());
      tracks.add(new Track.Builder(7).title("Dreams Finale").sampleClip(sampleClip).build());
      
      trackRepository.save(tracks);
     
      //Category
      AlbumCategory albumCat = new AlbumCategory.Builder("Blues").build();
      albumCategoryRepository.save(albumCat);
      
      ArtistInformation artInfo = new ArtistInformation.Builder("Jesse").lastname("Marks").build();
      artistInformationRepository.save(artInfo);
      
      ArrayList<Review> reviews = new ArrayList<Review>();
      reviews.add(new Review.Builder(10).description("Good").build());
      reviewRepository.save(reviews);
     
      MarketingInformation marketInfo = new MarketingInformation.Builder("Approved").artistInfo(artInfo).review(reviews).build();
      marketingInformationRepository.save(marketInfo);
              
      Album album = new Album.Builder("Dreams Special Edition")
              .inventory(120)
              .marketingInfo(marketInfo)
              .sales(100)
              .track(tracks)
              .unitPrice(new BigDecimal(200.00))
              .category(albumCat)
              .build();
      
     albumRepository.save(album);

      OrderItem updatedItem = new OrderItem.Builder(1)
                            .OrderItem(item)
                            .album(album)
                            .build();
      
      orderItemRepository.save(updatedItem);
      
      OrderItem newItem = orderItemRepository.findOne(id);
      
      Assert.assertEquals(newItem.getAlbum().getAlbumTitle(), "Dreams Special Edition");
   }
    
  @Test(dependsOnMethods = "updateOrderItem")
   public void deleteOrderItem(){
       orderItemRepository = ctx.getBean(OrderItemRepository.class);
       OrderItem item = orderItemRepository.findOne(id);
       orderItemRepository.delete(item);
       OrderItem deletedItem = orderItemRepository.findOne(id);
       Assert.assertNull(deletedItem); 
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
