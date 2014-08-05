package com.jonathan.startup.repository;

import com.jonathan.startup.domain.ArtistInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface ArtistInformationRepository extends JpaRepository<ArtistInformation, Long>{
    
}
