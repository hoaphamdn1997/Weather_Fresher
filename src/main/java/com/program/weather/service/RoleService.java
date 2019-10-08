package com.program.weather.service;

import com.program.weather.entity.RoleEntity;

import java.util.List;

public interface RoleService {
	/**
	 * find all roles
	 * @return
	 */
	List<RoleEntity> findAll();
}
