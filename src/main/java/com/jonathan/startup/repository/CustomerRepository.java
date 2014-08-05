package com.jonathan.startup.repository;

import com.jonathan.startup.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{    
}
