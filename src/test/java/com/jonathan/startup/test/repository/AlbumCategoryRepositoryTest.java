package com.jonathan.startup.test.repository;

import com.jonathan.startup.domain.AlbumCategory;
import com.jonathan.startup.repository.AlbumCategoryRepository;
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
public class AlbumCategoryRepositoryTest {
    public static ApplicationContext ctx;
    private AlbumCategoryRepository albumCategoryRepository;
    private Long id;
    
    public AlbumCategoryRepositoryTest() {
    }

     @Test
     public void createAlbumCategory() {
      albumCategoryRepository = ctx.getBean(AlbumCategoryRepository.class);
      
      AlbumCategory albumCat = new AlbumCategory.Builder("Jazz").build();
      albumCategoryRepository.save(albumCat);
      
      id = albumCat.getId();
      Assert.assertNotNull(id);
   }
     
   @Test(dependsOnMethods = "createAlbumCategory")
     public void readAlbumCategory(){
      albumCategoryRepository = ctx.getBean(AlbumCategoryRepository.class);
      AlbumCategory albumCat = albumCategoryRepository.findOne(id);
      Assert.assertEquals(albumCat.getCategoryName(), "Jazz");
   }
     
   @Test(dependsOnMethods = "readAlbumCategory")
     public void updateAlbumCategory(){
      albumCategoryRepository = ctx.getBean(AlbumCategoryRepository.class);
      AlbumCategory albumCat = albumCategoryRepository.findOne(id);

      AlbumCategory updatedAlbumCat = new AlbumCategory.Builder("Jazz")
              .albumCategory(albumCat)
              .categoryName("New Jazz")
              .build();
      albumCategoryRepository.save(updatedAlbumCat);   
      AlbumCategory newAlbumCat = albumCategoryRepository.findOne(id);
      
      Assert.assertEquals(newAlbumCat.getCategoryName(), "New Jazz");
   }
     
    @Test(dependsOnMethods = "updateAlbumCategory")
     public void deleteAlbumCategory(){
      albumCategoryRepository = ctx.getBean(AlbumCategoryRepository.class);
      AlbumCategory albumCat = albumCategoryRepository.findOne(id);
      albumCategoryRepository.delete(albumCat);

      AlbumCategory deletedAlbumCat = albumCategoryRepository.findOne(id);
      Assert.assertNull(deletedAlbumCat);
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
