package com.jonathan.startup.repository;

import com.jonathan.startup.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface TrackRepository extends JpaRepository<Track, Long>{  
}
