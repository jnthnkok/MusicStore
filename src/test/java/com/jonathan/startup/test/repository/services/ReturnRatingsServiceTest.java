/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.startup.test.repository.services;
import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.Contact;
import com.jonathan.startup.domain.Customer;
import com.jonathan.startup.domain.CustomerAddress;
import com.jonathan.startup.domain.MarketingInformation;
import com.jonathan.startup.domain.Name;
import com.jonathan.startup.domain.Review;
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
import com.jonathan.startup.test.ConnectionConfigTest;
import static com.jonathan.startup.test.repository.AlbumRepositoryTest.ctx;
import static com.jonathan.startup.test.repository.services.ContactServiceTest.ctx;
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
public class ReturnRatingsServiceTest {
        public static ApplicationContext ctx;
        public ReturnRatingsService service;
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
    
    public ReturnRatingsServiceTest() {
    }
    
    @Test
    public void getRatingServiceTest(){
      service = ctx.getBean(ReturnRatingsService.class);
      marketingInformationRepository = ctx.getBean(MarketingInformationRepository.class);
      reviewRepository = ctx.getBean(ReviewRepository.class);
      
      Review review = new Review.Builder(10).build();
      reviewRepository.save(review);
     
      ArrayList<Review> rList = new ArrayList<Review>();
      rList.add(review);
              
      MarketingInformation info = new MarketingInformation.Builder("Approved").review(rList).build();
      marketingInformationRepository.save(info);
      
      Album album = new Album.Builder("A New Day").marketingInfo(info).build();
      service.persist(album);
      
      ArrayList<String> strRatings = service.getRatings(album.getAlbumTitle());
      Assert.assertNotNull(strRatings);
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
      //  service = ctx.getBean(ReturnRatingsService.class);
       // List<Album> listAlbums = service.findAll();
        //for(Album a: listAlbums)
        // if(a != null)
         // /   service.remove(a);
    }
}
