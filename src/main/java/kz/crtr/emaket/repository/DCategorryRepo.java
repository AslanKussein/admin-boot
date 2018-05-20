package kz.crtr.emaket.repository;

import kz.crtr.emaket.entity.tbl.DCategory;
import kz.crtr.emaket.entity.tbl.TData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author a.kussein
 */
public interface DCategorryRepo extends JpaRepository<DCategory, Long> {
    List<DCategory> findAll();
    DCategory findById(int id);
}