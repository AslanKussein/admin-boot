package kz.crtr.emaket.repository;

import kz.crtr.emaket.entity.tbl.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author a.kussein
 */
public interface UserRepository extends JpaRepository<Users, Long>{
    Users findUsersByUName(String username);
}