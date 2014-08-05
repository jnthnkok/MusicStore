package com.jonathan.startup.repository;

import com.jonathan.startup.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    
}
