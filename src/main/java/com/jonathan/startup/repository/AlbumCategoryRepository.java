package com.jonathan.startup.repository;

import com.jonathan.startup.domain.AlbumCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface AlbumCategoryRepository extends JpaRepository<AlbumCategory, Long>{  
}
