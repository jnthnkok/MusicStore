package com.jonathan.startup.test.repository.services;

import com.jonathan.startup.domain.CreditCard;
import com.jonathan.startup.exceptions.CreditCardRejectionException;
import com.jonathan.startup.services.CreditCardService;
import com.jonathan.startup.test.ConnectionConfigTest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
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
public class CreditCardSampleServiceTest {

    public static ApplicationContext ctx;
    private Long id;
    private CreditCardService creditService;

    public CreditCardSampleServiceTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test(enabled = true)
    public void testForCreditApproval() {
        //Create Credit card Object 
        //Get The Service Objects
        creditService = ctx.getBean(CreditCardService.class);

        // DATE CONSTRUCTOR DateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour) 
        Date expiryDate = new DateTime(2014, 12, 26, 0, 0).toDate();

        //Create the Credit card Object using the Builder Pattern
        CreditCard creditCard = new CreditCard.Builder("222222222")
                .balance(new BigDecimal(20000.00))
                .expiryDate(expiryDate)
                .nameOnCreditCard("MR C.Phiri")
                .build();

        // STORE THE ID FOR LATER USE
        id = creditCard.getId();
        creditService.persist(creditCard);
        // Assert Creation
        Assert.assertNotNull(creditCard);
        // SUBMIT  Correct CREDIT CARD DETAILS 
        String status = creditService.processPayment("222222222", new BigDecimal("2000.00"), expiryDate);
        Assert.assertEquals(status, "APPROVED");

    }

    @Test(expectedExceptions = CreditCardRejectionException.class, enabled = true)
    public void testForWrongCreditCardNumber() {
        //Create Credit card Object 
        //Get The Service Objects 
        creditService = ctx.getBean(CreditCardService.class);

        //Create the Credit card Object using the Bulder pattern
        Date expiryDate = new DateTime(2014, 12, 26, 0, 0).toDate();

        CreditCard creditCard = new CreditCard.Builder("33333333")
                .balance(new BigDecimal(20000.00))
                .expiryDate(expiryDate)
                .nameOnCreditCard("MR C.Phiri")
                .build();
        // NO NEED TO STORE ID Because it will be available on the object to us
        //Since we are within the same method Scope
        creditService.persist(creditCard);
        // Assert Creation
        Assert.assertNotNull(creditCard);
        // SUBMIT  WRONG  CREDIT CARD NUMBER 
        String status = creditService.processPayment("99999", new BigDecimal("2000.00"), expiryDate);

    }

    @Test(expectedExceptions = CreditCardRejectionException.class)
    public void testForWrongCreditCardExpiryDate() {
        //Create Credit card Object 
        //Get The Service Objects 
        creditService = ctx.getBean(CreditCardService.class);
        //Create the Credit card Object using the Builder Pattern

        Date expiryDate = new DateTime(2012, 12, 26, 0, 0).toDate();

        CreditCard creditCard = new CreditCard.Builder("44444444")
                .balance(new BigDecimal(20000.00))
                .expiryDate(expiryDate)
                .nameOnCreditCard("MR C.Phiri")
                .build();

        // NO NEED TO STORE ID Because it will be available on the object to us
        //Since we are within the same method Scope
        creditService.persist(creditCard);
        // Assert Creation
        Assert.assertNotNull(creditCard);

        // SUBMIT  WRONG  CREDIT CARD Expiry Date 
        Date wrongDate = new DateTime(2016, 12, 26, 0, 0).toDate();
        String status = creditService.processPayment("44444444", new BigDecimal("2000.00"), wrongDate);

    }

    @Test(expectedExceptions = CreditCardRejectionException.class)
    public void testForInsufficientbalance() {
        //Create Credit card Object 
        //Get The Service Objects 
        creditService = ctx.getBean(CreditCardService.class);
        //Create the Credit card Object using the Builder Pattern
        Date expiryDate = new DateTime(2012, 12, 26, 0, 0).toDate();


        CreditCard creditCard = new CreditCard.Builder("6666666")
                .balance(new BigDecimal(20000.00))
                .expiryDate(expiryDate)
                .nameOnCreditCard("MR C.Phiri")
                .build();
        // NO NEED TO STORE ID Because it will be available on the object to us
        //Since we are within the same method Scope
        creditService.persist(creditCard);
        // Assert Creation
        Assert.assertNotNull(creditCard);

        // SUBMIT LARGER AMOUNT THAN BALANCE AVAILABLE ON THE CREDIT CARD 
        String status = creditService.processPayment("6666666", new BigDecimal("40000.00"), expiryDate);

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
        // UNCOMMENT TO DELETE ALL THE CRESDIT CARDS CREATED IN THE DATABASE
//        creditService = ctx.getBean(CreditCardService.class);
//        List<CreditCard> cards = creditService.findAll();
//        for (CreditCard creditCard : cards) {
//            creditService.remove(creditCard);
//
//        }
    }
}