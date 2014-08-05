package com.jonathan.startup.test.repository.services;
import com.jonathan.startup.domain.Contact;
import com.jonathan.startup.domain.Customer;
import com.jonathan.startup.domain.CustomerAddress;
import com.jonathan.startup.domain.Name;
import com.jonathan.startup.services.ContactService;
import com.jonathan.startup.test.ConnectionConfigTest;
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
public class ContactServiceTest {
    public static ApplicationContext ctx; 
    public ContactService service;
    
    public ContactServiceTest() {
    }

     @Test
     public void getAddressTest(){
         service = ctx.getBean(ContactService.class);
         
         CustomerAddress custAddr = new CustomerAddress.Builder("100 Blue Street")
                 .postalAddress("100 Blue Street")
                 .build();
         
         service.persistAddress(custAddr);
         
         Customer customer = new Customer.Builder("5001")
                 .name(new Name.Builder("John").lastName("Smith").build())
                 .customerAddress(custAddr)
                 .build();
         
         service.persist(customer);

         Assert.assertEquals(service.getAddress(customer.getCustomerNumber()), custAddr);   
     }
     
     @Test
     public void getCellphoneNumberTest(){
          service = ctx.getBean(ContactService.class);
          
          Customer customer = new Customer.Builder("5002")
                  .contact(new Contact.Builder("555-1234").cellNumber("0879998744").build())
                  .build();
          
         service.persist(customer);
         
         Assert.assertEquals(service.getCellphoneNumber(customer.getCustomerNumber()), customer.getContact().getCellNumber());
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
       service = ctx.getBean(ContactService.class);
        
        List<Customer> listCustomer = service.findAll();
        
        for(Customer c: listCustomer){
            service.remove(c);
        }
    }
}
