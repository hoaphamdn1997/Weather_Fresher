package com.program.weather.service.repository;

import com.program.weather.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    /**
     * Find follow role
     *
     * @param role the role
     * @return role RoleEntity
     */
    RoleEntity findByRole(String role);
}
