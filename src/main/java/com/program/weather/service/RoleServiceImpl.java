package com.program.weather.service;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.repository.RoleRepository;

import com.program.weather.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;

	/**
	 * Find All Role
	 * @return
	 */
	@Override
	public List<RoleEntity> findAll() {
		return roleRepository.findAll();
	}

}
