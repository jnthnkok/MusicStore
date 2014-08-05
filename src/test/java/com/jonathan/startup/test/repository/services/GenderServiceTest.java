package com.jonathan.startup.test.repository.services;

import com.jonathan.startup.domain.Album;
import com.jonathan.startup.domain.AlbumCategory;
import com.jonathan.startup.domain.ArtistInformation;
import com.jonathan.startup.domain.Contact;
import com.jonathan.startup.domain.CreditCard;
import com.jonathan.startup.domain.Customer;
import com.jonathan.startup.domain.CustomerAddress;
import com.jonathan.startup.domain.CustomerInvoice;
import com.jonathan.startup.domain.Demographic;
import com.jonathan.startup.domain.MarketingInformation;
import com.jonathan.startup.domain.Name;
import com.jonathan.startup.domain.OrderItem;
import com.jonathan.startup.domain.Orders;
import com.jonathan.startup.domain.Review;
import com.jonathan.startup.domain.SampleClip;
import com.jonathan.startup.domain.Track;
import com.jonathan.startup.repository.AlbumCategoryRepository;
import com.jonathan.startup.repository.AlbumRepository;
import com.jonathan.startup.repository.ArtistInformationRepository;
import com.jonathan.startup.repository.CreditCardRepository;
import com.jonathan.startup.repository.CustomerAddressRepository;
import com.jonathan.startup.repository.CustomerInvoiceRepository;
import com.jonathan.startup.repository.CustomerRepository;
import com.jonathan.startup.repository.MarketingInformationRepository;
import com.jonathan.startup.repository.OrderItemRepository;
import com.jonathan.startup.repository.OrderRepository;
import com.jonathan.startup.repository.ReviewRepository;
import com.jonathan.startup.repository.TrackRepository;
import com.jonathan.startup.services.ContactService;
import com.jonathan.startup.services.GenderService;
import com.jonathan.startup.services.Impl.ReturnRatingsServiceImpl;
import com.jonathan.startup.services.ReturnRatingsService;
import com.jonathan.startup.services.URLService;
import com.jonathan.startup.test.ConnectionConfigTest;
import static com.jonathan.startup.test.repository.AlbumRepositoryTest.ctx;
import static com.jonathan.startup.test.repository.OrderRepositoryTest.ctx;
import static com.jonathan.startup.test.repository.services.ContactServiceTest.ctx;
import static com.jonathan.startup.test.repository.services.ReturnRatingsServiceTest.ctx;
import static com.jonathan.startup.test.repository.services.URLServiceTest.ctx;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class GenderServiceTest {
        public static ApplicationContext ctx;
        public GenderService service;
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
        private CustomerRepository customerRepository;
        private CustomerAddressRepository customerAddressRepository;
        private CreditCardRepository creditCardRepository;
  
    public GenderServiceTest() {
    }


    @Test
     public void getAlbumsGender(){
       service = ctx.getBean(GenderService.class);
      orderRepository = ctx.getBean(OrderRepository.class);
      albumCategoryRepository = ctx.getBean(AlbumCategoryRepository.class);
      orderItemRepository = ctx.getBean(OrderItemRepository.class);
      marketingInformationRepository = ctx.getBean(MarketingInformationRepository.class);
      albumRepository = ctx.getBean(AlbumRepository.class);
      trackRepository = ctx.getBean(TrackRepository.class);
      artistInformationRepository = ctx.getBean(ArtistInformationRepository.class);
      reviewRepository = ctx.getBean(ReviewRepository.class);
      customerInvoiceRepository = ctx.getBean(CustomerInvoiceRepository.class);
     
        customerRepository = ctx.getBean(CustomerRepository.class);
        customerAddressRepository = ctx.getBean(CustomerAddressRepository.class); 
        creditCardRepository = ctx.getBean(CreditCardRepository.class);
        orderRepository = ctx.getBean(OrderRepository.class);
        customerInvoiceRepository = ctx.getBean(CustomerInvoiceRepository.class);
        orderItemRepository = ctx.getBean(OrderItemRepository.class);
        
        //Embedded
        Name name = new Name.Builder("Barry").lastName("Allen").build();
        Demographic demographic = new Demographic.Builder("Male")
                .race("White")
                .birth(new Date())
                .build();
        Contact contact = new Contact.Builder("555-1234")
                .cellNumber("0111111111")
                .build();
        
        CustomerAddress customerAddress = new CustomerAddress.Builder("151 Central City")
                .postalAddress("5151 Central City")
                .build();
        customerAddressRepository.save(customerAddress);
        
        CreditCard creditCard = new CreditCard.Builder("1111-2222-3333-7777")
                .balance(new BigDecimal(1000.00))
                .expiryDate(new Date())
                .nameOnCreditCard("Barry Allen").build();
        
         List<CreditCard> listCreditCard = new ArrayList<>();
         listCreditCard.add(creditCard);
         
        creditCardRepository.save(creditCard);
        
        
        CustomerInvoice customerInvoice = new CustomerInvoice.Builder(BigDecimal.valueOf(12500.00))
                .invoiceDate(new Date())
                .invoiceStatus("Approved").build();
          
        customerInvoiceRepository.save(customerInvoice);
        
        OrderItem item = new OrderItem.Builder(5).build();
        List<OrderItem> listOrderItems = new ArrayList<>();
        listOrderItems.add(item);
        
        orderItemRepository.save(item);
        
        Orders order = new Orders.Builder(1)
                .invoice(customerInvoice)
                .orderItem(listOrderItems)
                .orderDate(new Date())
                .build();
        
        List<Orders> listOrder = new ArrayList<>();      
        listOrder.add(order);
         
        orderRepository.save(order);
        
        Customer customer = new Customer.Builder("1001")
                .name(name)
                .customerAddress(customerAddress)
                .contact(contact)
                .creditCard(listCreditCard)
                .order(listOrder)
                .demographic(demographic)
                .build();

        customerRepository.save(customer);
     
      ArrayList<Album> listAlbums = service.getAlbumGender("Male");
         
      Assert.assertNotNull(listAlbums);   
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
