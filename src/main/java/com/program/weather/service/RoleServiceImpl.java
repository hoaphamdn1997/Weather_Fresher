package com.program.weather.service;

import com.program.weather.entity.RoleEntity;
import com.program.weather.service.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Role service.
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * The Role repository.
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * Find All Role
     *
     * @return List Role
     */
    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

}
