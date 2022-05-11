package com.inacioalves.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inacioalves.auth.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	@Query("SELECT p FROM Permission p WHERE p.description =:description")
	Permission findByDescription(@Param("description") String description);
}
