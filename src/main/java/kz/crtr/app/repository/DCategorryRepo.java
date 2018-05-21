package kz.crtr.app.repository;

import kz.crtr.app.entity.tbl.DCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author a.kussein
 */
public interface DCategorryRepo extends JpaRepository<DCategory, Long> {
    List<DCategory> findAll();
    DCategory findById(int id);
}