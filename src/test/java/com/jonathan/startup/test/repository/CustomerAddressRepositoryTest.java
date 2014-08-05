package com.jonathan.startup.test.repository;

import com.jonathan.startup.domain.CustomerAddress;
import com.jonathan.startup.repository.CustomerAddressRepository;
import com.jonathan.startup.test.ConnectionConfigTest;
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
public class CustomerAddressRepositoryTest {
    public static ApplicationContext ctx;
    private CustomerAddressRepository customerAddressRepository;
    private Long id;
    
    public CustomerAddressRepositoryTest() {
    }

    @Test
    public void createCustomerAddress() {
     customerAddressRepository = ctx.getBean(CustomerAddressRepository.class); 
     CustomerAddress customerAddress = new CustomerAddress.Builder("151 Red Street")
                .postalAddress("151 Red City")
                .build();
     customerAddressRepository.save(customerAddress);
     id = customerAddress.getId();
     Assert.assertNotNull(id);
    }
    
    @Test(dependsOnMethods = "createCustomerAddress")
    public void readCustomerAddress(){
    customerAddressRepository = ctx.getBean(CustomerAddressRepository.class); 
    CustomerAddress customerAddress = customerAddressRepository.findOne(id);
    Assert.assertEquals(customerAddress.getStreetAddress(), "151 Red Street");  
    }
    
    @Test(dependsOnMethods = "readCustomerAddress")
    public void updateCustomerAddress(){
    customerAddressRepository = ctx.getBean(CustomerAddressRepository.class); 
    CustomerAddress customerAddress = customerAddressRepository.findOne(id);
    
    CustomerAddress updatedCustomerAddress = new CustomerAddress.Builder("151 Red Street")
                .CustomerAddress(customerAddress)
                .postalAddress("700 Red Street")
                .build();
 
    customerAddressRepository.save(updatedCustomerAddress);
    
    CustomerAddress newCustomerAddress = customerAddressRepository.findOne(id);

    Assert.assertEquals(updatedCustomerAddress.getPostalAddress(), "700 Red Street");  
    }
    
   @Test(dependsOnMethods = "updateCustomerAddress")
    public void deleteCustomerAddress(){
    customerAddressRepository = ctx.getBean(CustomerAddressRepository.class); 
    
    CustomerAddress customerAddress = customerAddressRepository.findOne(id);
    customerAddressRepository.delete(customerAddress);
    
    CustomerAddress deletedCustomerAddress = customerAddressRepository.findOne(id);
    Assert.assertNull(deletedCustomerAddress);
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
