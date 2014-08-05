package com.jonathan.startup.repository;

import com.jonathan.startup.domain.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long>{
    
}
