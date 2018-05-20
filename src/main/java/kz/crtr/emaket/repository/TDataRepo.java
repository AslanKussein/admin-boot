package kz.crtr.emaket.repository;

import kz.crtr.emaket.entity.tbl.TData;
import kz.crtr.emaket.entity.tbl.TImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author a.kussein
 */
public interface TDataRepo extends JpaRepository<TData, Long> {

    List<TData> findAllByCatId(int id);
    List<TData> findAllByNameContaining(String name);
}