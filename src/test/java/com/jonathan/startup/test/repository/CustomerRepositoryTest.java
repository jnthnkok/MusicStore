
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
public class CustomerRepositoryTest {
    private static ApplicationContext ctx;
    private Long id;
    private CustomerRepository customerRepository;
    private CustomerAddressRepository customerAddressRepository;
    private CreditCardRepository creditCardRepository;
    private OrderRepository orderRepository;
    private CustomerInvoiceRepository customerInvoiceRepository;
    private OrderItemRepository orderItemRepository;
    
    public CustomerRepositoryTest() {
    }
    //Create
    @Test
    public void createCustomer(){
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
        id = customer.getId();
        
        Assert.assertNotNull(id);
    }
    //Read
    @Test(dependsOnMethods = "createCustomer")
    public void readCustomer(){
        customerRepository = ctx.getBean(CustomerRepository.class);
        Customer customer = customerRepository.findOne(id);
        Assert.assertEquals(customer.getCustomerNumber(), "1001");
        Assert.assertEquals(customer.getName().getFirstName(), "Barry");
    }
    //Update
    @Test(dependsOnMethods = "readCustomer")
    private void updateCustomer(){
        customerRepository = ctx.getBean(CustomerRepository.class);
        Customer customer = customerRepository.findOne(id);
        
        CustomerAddress newAddress = new CustomerAddress.Builder("800 Central City")
                .postalAddress("800 Central City")
                .build();
        
        customerAddressRepository.save(newAddress);
                
        Customer customerUpdate = new Customer.Builder("1001")
                .customer(customer)
                .customerAddress(newAddress)
                .build();
        
        customerRepository.save(customerUpdate);
        
        Customer newCust = customerRepository.findOne(id);
        Assert.assertEquals(newCust.getCustomerAddress().getStreetAddress(), "800 Central City");
    }

    @Test(dependsOnMethods = "updateCustomer")
    private void deleteCustomer(){
        customerRepository = ctx.getBean(CustomerRepository.class);
        Customer customer = customerRepository.findOne(id);
        customerRepository.delete(customer);
        Customer customerDeleted = customerRepository.findOne(id);
        Assert.assertNull(customerDeleted);
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
