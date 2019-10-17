package com.program.weather.controller;

import java.security.Principal;

import java.util.List;

import java.util.stream.Collectors;


import com.program.weather.entity.UserEntity;
import com.program.weather.service.RoleService;
import com.program.weather.service.UserService;
import com.program.weather.service.converter.UserConverter;
import com.program.weather.service.dto.UserDTO;
import com.program.weather.entity.RoleEntity;
import com.program.weather.service.dto.property.AdminDeleteDTO;
import com.program.weather.service.dto.property.UserRestDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/home-admin")
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserConverter userConverter;

    /**
     * Go to the Page ADMIN
     *
     * @param model
     * @param principal
     * @return Page Admin
     */
    @GetMapping("/admin")
    public String homeAdmin(Model model, Principal principal) {
        //List User
        List<UserDTO> dsUser = userService.findAll().stream()
                .map(x -> userConverter.convertUserToDTO(x))
                .collect(Collectors.toList());
        //list Role
        List<RoleEntity> dsRole = roleService.findAll();
        //ATTRIBUTE
        model.addAttribute("dsUser", dsUser);
        model.addAttribute("dsRole", dsRole);
        return "pageAdmin";
    }


    /**
     * Delete User By ID
     *
     * @param adminDTO
     */
    @DeleteMapping("/delete")
    @ResponseBody
    public void deleteUser(@RequestBody AdminDeleteDTO adminDTO) {
        userService.delete(adminDTO.getId());
    }

    /**
     * Edit Status User -> active or Unactive
     *
     * @param id
     */
    @GetMapping("/editActiveUserA")
    @ResponseBody
    public void edit(@RequestParam Long id,Model model) {
        userService.editActiveUser(id);
    }

    /**
     * Change Role By Admin
     *
     * @param userRestDTO
     */
    @PutMapping("/change-role")
    @ResponseBody
    public UserEntity changeRole(@RequestBody UserRestDTO userRestDTO) {
        userService.editRoleUser(userRestDTO.getId(), userRestDTO.getRole());
        return userService.findByUserId(userRestDTO.getId());
    }
}






