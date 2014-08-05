package com.jonathan.startup.test.repository;
import com.jonathan.startup.config.ConnectionConfig;
import com.jonathan.startup.domain.SampleClip;
import com.jonathan.startup.domain.Track;
import com.jonathan.startup.repository.TrackRepository;
import com.jonathan.startup.test.ConnectionConfigTest;
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
public class TrackRepositoryTest {
    public static ApplicationContext ctx;
    private TrackRepository trackRepository;
    private Long id;
    
    public TrackRepositoryTest() {
    }

     @Test
     public void createTrack(){
         trackRepository = ctx.getBean(TrackRepository.class);
         SampleClip sampleClip = new SampleClip.Builder("www.youtube.com").build();
         Track track = new Track.Builder(1)       
                 .title("Interlude")
                 .sampleClip(sampleClip)
                 .build();
         
         trackRepository.save(track);
         
         id = track.getId();
         
         Assert.assertNotNull(id);
     }
          
     @Test(dependsOnMethods = "createTrack")
     public void readTrack(){
         trackRepository = ctx.getBean(TrackRepository.class);
         Track track = trackRepository.findOne(id);
         Assert.assertEquals(track.getTrackTitle(), "Interlude"); 
     }
     
     @Test(dependsOnMethods = "readTrack")
     public void updateTrack(){
         trackRepository = ctx.getBean(TrackRepository.class);
         Track track = trackRepository.findOne(id);
         Track updatedTrack = new Track.Builder(1)
                 .track(track)
                 .title("Track 1")
                 .build();
         
         trackRepository.save(updatedTrack);
         
         Track newTrack = trackRepository.findOne(id);
         Assert.assertEquals(newTrack.getTrackTitle(), "Track 1");   
   }
     
     @Test(dependsOnMethods = "updateTrack")
     public void deleteTrack(){
         trackRepository = ctx.getBean(TrackRepository.class);
         Track track = trackRepository.findOne(id);
         trackRepository.delete(track);
         Track deletedTrack = trackRepository.findOne(id);
         Assert.assertNull(deletedTrack);       
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
