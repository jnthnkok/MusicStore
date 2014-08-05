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
public class ArtistInformationRepositoryTest {
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
    
    public ArtistInformationRepositoryTest() {
    }

     @Test
     public void createArtistInformation() {
     artistInformationRepository = ctx.getBean(ArtistInformationRepository.class);
     
     ArtistInformation artInfo = new ArtistInformation.Builder("Jeff").lastname("Snow").build();
     artistInformationRepository.save(artInfo);
     id = artInfo.getId();
     
     Assert.assertNotNull(id);
     }

     @Test(dependsOnMethods = "createArtistInformation")
     public void readArtistInformation() {
     artistInformationRepository = ctx.getBean(ArtistInformationRepository.class);
     ArtistInformation artInfo = new ArtistInformation.Builder("Jeff").lastname("Snow").build();
     artistInformationRepository.save(artInfo);
     
     Assert.assertEquals(artInfo.getFirstName(), "Jeff");
     }
     
     @Test(dependsOnMethods = "readArtistInformation")
     public void updateArtistInformation() {
     artistInformationRepository = ctx.getBean(ArtistInformationRepository.class);
     ArtistInformation artInfo = artistInformationRepository.findOne(id);
     
     ArtistInformation updatedArtInfo = new ArtistInformation.Builder("Jeff")
             .ArtistInformation(artInfo)
             .lastname("Tree")
             .build();
     artistInformationRepository.save(updatedArtInfo);
     
     ArtistInformation newArtInfo = artistInformationRepository.findOne(id);
     Assert.assertEquals(newArtInfo.getLastName(),"Tree");
     }
     
     @Test(dependsOnMethods = "updateArtistInformation")
     public void deleteArtistInformation() {
     artistInformationRepository = ctx.getBean(ArtistInformationRepository.class);
     ArtistInformation artInfo = artistInformationRepository.findOne(id);
     artistInformationRepository.delete(artInfo);
     
     ArtistInformation deletedArtInfo = artistInformationRepository.findOne(id);
     Assert.assertNull(deletedArtInfo);
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
