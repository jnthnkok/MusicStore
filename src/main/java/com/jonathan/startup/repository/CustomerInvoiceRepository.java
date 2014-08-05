package com.jonathan.startup.repository;

import com.jonathan.startup.domain.CustomerInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice, Long>{
    
}
