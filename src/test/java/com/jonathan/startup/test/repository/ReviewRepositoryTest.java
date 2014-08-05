package com.jonathan.startup.test.repository;
import com.jonathan.startup.domain.Review;
import com.jonathan.startup.repository.ReviewRepository;
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
public class ReviewRepositoryTest {
   public static ApplicationContext ctx;
   private Long id;
   private ReviewRepository reviewRepository;
    
   public ReviewRepositoryTest() {
   }
 
    @Test
    public void createReview() { 
        reviewRepository = ctx.getBean(ReviewRepository.class); 
        Review review = new Review.Builder(10)
                .description("Worth a listen")
                .build(); 
        reviewRepository.save(review); 
        id = review.getId(); 
        Assert.assertNotNull(id);

    }

    @Test(dependsOnMethods = "createReview")
    public void readReview() {
        reviewRepository = ctx.getBean(ReviewRepository.class);
        Review review = reviewRepository.findOne(id);
        Assert.assertEquals(review.getRating(), 10);
    }

    @Test(dependsOnMethods = "readReview")
    private void updateReview() {
        reviewRepository = ctx.getBean(ReviewRepository.class); 
        Review review = reviewRepository.findOne(id); 
        Review updatedReview = new Review.Builder(10)
                .Review(review) 
                .description("Masterpiece")
                .build();
        
        reviewRepository.save(updatedReview); 
        
        Review newReview = reviewRepository.findOne(id);
        Assert.assertEquals(newReview.getDescription(), "Masterpiece");

    }

    @Test(dependsOnMethods = "updateReview")
    private void deleteReview() {

        reviewRepository = ctx.getBean(ReviewRepository.class); 
        Review review = reviewRepository.findOne(id); 
        reviewRepository.delete(review); 
        Review deletedReview = reviewRepository.findOne(id); 
        Assert.assertNull(deletedReview);

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
