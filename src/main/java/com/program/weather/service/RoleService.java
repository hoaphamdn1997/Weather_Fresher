package com.program.weather.service;

import com.program.weather.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    /**
     * find all roles in Database
     *
     * @return List RoleEntity
     */
    List<RoleEntity> findAll();
}
