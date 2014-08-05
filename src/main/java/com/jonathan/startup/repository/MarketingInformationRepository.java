package com.jonathan.startup.repository;

import com.jonathan.startup.domain.MarketingInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface MarketingInformationRepository extends JpaRepository<MarketingInformation, Long>{
    
}
