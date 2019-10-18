package com.program.weather.entity.repository;

import com.program.weather.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
	/**
	 * Find follow role
	 * @param role
	 * @return role RoleEntity
	 */
	 RoleEntity findByRole(String role);
}
