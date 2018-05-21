package kz.crtr.app.repository;

import kz.crtr.app.entity.tbl.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author a.kussein
 */
public interface UserRepository extends JpaRepository<Users, Long>{
    Users findUsersByUName(String username);
}