package com.jonathan.startup.test.repository;

import com.jonathan.startup.domain.Contact;
import com.jonathan.startup.domain.CreditCard;
import com.jonathan.startup.domain.Customer;
import com.jonathan.startup.domain.CustomerAddress;
import com.jonathan.startup.domain.CustomerInvoice;
import com.jonathan.startup.domain.Demographic;
import com.jonathan.startup.domain.Name;
import com.jonathan.startup.domain.OrderItem;
import com.jonathan.startup.repository.CreditCardRepository;
import com.jonathan.startup.repository.CustomerAddressRepository;
import com.jonathan.startup.repository.CustomerInvoiceRepository;
import com.jonathan.startup.repository.CustomerRepository;
import com.jonathan.startup.repository.OrderItemRepository;
import com.jonathan.startup.repository.OrderRepository;
import com.jonathan.startup.test.ConnectionConfigTest;
import com.jonathan.startup.domain.Orders;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class CustomerInvoiceRepositoryTest {
    private static ApplicationContext ctx;
    private Long id;
    private CustomerRepository customerRepository;
    private CustomerAddressRepository customerAddressRepository;
    private CreditCardRepository creditCardRepository;
    private OrderRepository orderRepository;
    private CustomerInvoiceRepository customerInvoiceRepository;
    private OrderItemRepository orderItemRepository;
    public CustomerInvoiceRepositoryTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
    public void createCustomerInvoice() {       
        customerInvoiceRepository = ctx.getBean(CustomerInvoiceRepository.class);
        CustomerInvoice invoice = new CustomerInvoice.Builder(new BigDecimal(300.00)).invoiceDate(new Date()).invoiceStatus("UNPAID").build();
        customerInvoiceRepository.save(invoice);
        
        id = invoice.getId();
        Assert.assertNotNull(id);
    }
    
    @Test(dependsOnMethods = "createCustomerInvoice")
    public void readCustomerInvoice(){
        customerInvoiceRepository = ctx.getBean(CustomerInvoiceRepository.class);
        CustomerInvoice invoice = customerInvoiceRepository.findOne(id);
        Assert.assertEquals(invoice.getInvoiceStatus(), "UNPAID");  
    }

    @Test(dependsOnMethods = "readCustomerInvoice")
    public void updateCustomerInvoice(){
        customerInvoiceRepository = ctx.getBean(CustomerInvoiceRepository.class);
        CustomerInvoice invoice = customerInvoiceRepository.findOne(id);
        
        CustomerInvoice updatedInvoice = new CustomerInvoice.Builder(new BigDecimal(300.00))
                .CustomerInvoice(invoice)
                .invoiceStatus("PAID").build(); 
        
        customerInvoiceRepository.save(updatedInvoice);
        CustomerInvoice newInvoice = customerInvoiceRepository.findOne(id);
        
        Assert.assertEquals(newInvoice.getInvoiceStatus(), "PAID");
    }
    
    @Test(dependsOnMethods = "updateCustomerInvoice")
        public void deleteCustomerInvoice(){
        customerInvoiceRepository = ctx.getBean(CustomerInvoiceRepository.class);
        CustomerInvoice invoice = customerInvoiceRepository.findOne(id);
        customerInvoiceRepository.delete(invoice);
        
        CustomerInvoice deletedInvoice = customerInvoiceRepository.findOne(id);
        Assert.assertNull(deletedInvoice);
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
