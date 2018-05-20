package kz.crtr.emaket.repository;

import kz.crtr.emaket.entity.tbl.TImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author a.kussein
 */
public interface TImageRepo extends JpaRepository<TImage, Long> {
    TImage findAllById(String id);
}