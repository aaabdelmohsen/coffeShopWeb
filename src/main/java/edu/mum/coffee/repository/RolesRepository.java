package edu.mum.coffee.repository;

import edu.mum.coffee.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<UserRole, Integer> {
}
