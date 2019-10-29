package com.program.weather.controller;

import java.security.Principal;

import java.util.List;

import java.util.stream.Collectors;


import com.program.weather.entity.UserEntity;
import com.program.weather.service.RoleService;
import com.program.weather.service.UserService;
import com.program.weather.service.mapper.UserConverter;
import com.program.weather.service.dto.UserDTO;
import com.program.weather.entity.RoleEntity;
import com.program.weather.service.dto.property.AdminDeleteDTO;
import com.program.weather.service.dto.property.UserRestDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * The type Admin action controller.
 */
@Controller
@RequestMapping("/home-admin")
public class AdminActionController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserConverter userConverter;

    /**
     * Go to the Page ADMIN
     *
     * @param model     the model
     * @param principal the principal
     * @return Page Admin
     */
    @GetMapping("/admin")
    public String homeAdmin(Model model, Principal principal) {
        //List all User
        List<UserDTO> dsUser = userService.findAll().stream()
                .map(x -> userConverter.convertUserToDTO(x))
                .collect(Collectors.toList());
        //list all Role
        List<RoleEntity> dsRole = roleService.findAll();
        //ATTRIBUTE
        model.addAttribute("dsUser", dsUser);
        model.addAttribute("dsRole", dsRole);
        return "admin/pageAdmin";
    }


    /**
     * Delete User By ID User
     * <p>
     * An @ResponseBody annotation is added before the controller
     * methods to indicate that this method will return text instead of view.
     *
     * @param adminDTO the admin dto
     */
    @DeleteMapping("/delete")
    @ResponseBody
    public void deleteUser(@RequestBody AdminDeleteDTO adminDTO) {
        userService.delete(adminDTO.getId());
    }

    /**
     * Edit Status User -> active or Unactive
     *
     * @param id the id
     */
    @GetMapping("/editActiveUserA")
    @ResponseBody
    public void edit(@RequestParam Long id) {
        userService.editActiveUser(id);
    }

    /**
     * Change Role By Admin
     * Change role for user ->ROLE_ADMIN;ROLE_USER...
     *
     * @param userRestDTO the user rest dto
     * @return the user entity
     */
    @PutMapping("/change-role")
    @ResponseBody
    public UserEntity changeRole(@RequestBody UserRestDTO userRestDTO) {
        userService.editRoleUser(userRestDTO.getId(), userRestDTO.getRole());
        return userService.findByUserId(userRestDTO.getId());
    }
}






