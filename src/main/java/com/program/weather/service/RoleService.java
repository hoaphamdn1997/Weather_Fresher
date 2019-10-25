package com.program.weather.service;

import com.program.weather.entity.RoleEntity;

import java.util.List;

/**
 * The interface Role service.
 */
public interface RoleService {
    /**
     * find all roles in Database
     *
     * @return List RoleEntity
     */
    List<RoleEntity> findAll();
}
