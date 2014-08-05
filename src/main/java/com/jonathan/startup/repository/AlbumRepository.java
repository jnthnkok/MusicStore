package com.jonathan.startup.repository;
import com.jonathan.startup.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jonathan Kok
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{
    
}
