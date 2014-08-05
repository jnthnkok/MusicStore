package com.jonathan.startup.repository;

import com.jonathan.startup.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}
