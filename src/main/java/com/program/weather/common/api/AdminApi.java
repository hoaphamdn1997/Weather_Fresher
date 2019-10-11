package com.program.weather.common.api;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import com.program.weather.service.RoleService;
import com.program.weather.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminApi {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    /**
     * Delete User by id
     *
     * @param id
     */
    public void deleteUser(long id) {
        userService.delete(id);
    }

    /**
     * findAll Usser
     *
     * @return
     */

    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    /**
     * Edit Status User
     *
     * @param id
     */

    public void editActiveUser(Long id) {
        userService.editActiveUser(id);
    }

    /**
     * edit Role User
     *
     * @param id
     * @param role
     */
    public void editRoleUser(Long id, String role) {
        userService.editRoleUser(id, role);
    }

    /**
     * Find All Role
     *
     * @return findAllRole
     */
    public List<RoleEntity> findAllRole() {
        return roleService.findAll();
    }

}
