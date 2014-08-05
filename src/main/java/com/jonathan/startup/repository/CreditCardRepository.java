package com.jonathan.startup.repository;

import com.jonathan.startup.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{    
}
