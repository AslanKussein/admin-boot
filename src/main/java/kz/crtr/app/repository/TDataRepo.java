package kz.crtr.app.repository;

import kz.crtr.app.entity.tbl.TData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author a.kussein
 */
public interface TDataRepo extends JpaRepository<TData, Long> {

    List<TData> findAllByCatId(int id);
    List<TData> findAllByNameContaining(String name);
}