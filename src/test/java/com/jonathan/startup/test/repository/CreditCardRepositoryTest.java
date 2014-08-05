package com.jonathan.startup.test.repository;

import com.jonathan.startup.domain.CreditCard;
import com.jonathan.startup.repository.CreditCardRepository;
import com.jonathan.startup.test.ConnectionConfigTest;
import java.math.BigDecimal;
import java.util.Date;
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
 * @author hashcode
 */
public class CreditCardRepositoryTest {

    public static ApplicationContext ctx;
    private Long id;
    private CreditCardRepository creditCardRepository;

    public CreditCardRepositoryTest() {
    }

    @Test(enabled = true)
    public void createCreditCard() {
        // *** (i) Create The Entity
        //Get The Repository Object 
        creditCardRepository = ctx.getBean(CreditCardRepository.class);
           //Create the Credit card Object using the Builder Design Pattern with ID. It will be generated
        // USE BUILDER PATTERN FOR CREATING ALL YOUR ENTITIES
        CreditCard creditCard = new CreditCard.Builder("5555-555-555-55555")
                .balance(new BigDecimal(200.00))
                .expiryDate(new Date())
                .nameOnCreditCard("John Dole")
                .build();
        //SAVE THE CREDIT CARD OBJECT 
        creditCardRepository.save(creditCard);
        // STORE THE ID FOR LATER USE
        id = creditCard.getId();
        // ASSERT THAT THE OBJECT IS SAVED
        Assert.assertNotNull(id);
    }

    @Test(dependsOnMethods = "createCreditCard", enabled = true)
    public void readCreditCard() {
        creditCardRepository = ctx.getBean(CreditCardRepository.class);
        CreditCard creditcard = creditCardRepository.findOne(id);
        Assert.assertEquals(creditcard.getNameOnCreditCard(), "John Dole");
    }

    @Test(dependsOnMethods = "readCreditCard", enabled = true)
    private void updateCreditCard() {
        creditCardRepository = ctx.getBean(CreditCardRepository.class);
        //GET THE OBJECT 
        CreditCard creditCard = creditCardRepository.findOne(id);
        //CREATAE A NEW COPY 
        CreditCard updatedCreditCard = new CreditCard.Builder("5555-555-555-55555")
                .CreditCard(creditCard)
                .nameOnCreditCard("MR JOE DOLE")
                .build();

        //SAVE THE NEW COPY
        creditCardRepository.save(updatedCreditCard);
        // GET NEW CARD AND TEST 
        CreditCard newCreditCard = creditCardRepository.findOne(id);
        Assert.assertEquals(newCreditCard.getNameOnCreditCard(), "MR JOE DOLE");

    }

    @Test(dependsOnMethods = "updateCreditCard", enabled = true)
    private void deleteCreditCard() {

        creditCardRepository = ctx.getBean(CreditCardRepository.class);
        //GET OBJECT 
        CreditCard person = creditCardRepository.findOne(id);
        //DELETE OBJECT 
        creditCardRepository.delete(person);
        // TRY GETTING THE OBJECT 
        CreditCard deletedCreditCard = creditCardRepository.findOne(id);
        // CHECK IF YOU GOT NOTHING 
        Assert.assertNull(deletedCreditCard);

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        // INITIALISE THE TEST CONTESXT
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
