package com.program.weather.controller;

import java.security.Principal;
import java.util.List;

import com.program.weather.common.api.AdminApi;

import com.program.weather.entity.RoleEntity;
import com.program.weather.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/home-admin")
public class AdminController {


	@Autowired
	private AdminApi adminApi;

	/**
	 * Go to the Page ADMIN
	 * @param model
	 * @param principal
	 * @return Page Admin
	 */
	@GetMapping("/admin")
	public String homeAdmin(Model model , Principal principal) {
		//List User
		List<UserEntity> dsUser = adminApi.findAll();
		//list Role
		List<RoleEntity> dsRole = adminApi.findAllRole();
		//ATTRIBUTE
		model.addAttribute("dsUser",dsUser);
		model.addAttribute("dsRole", dsRole);
		return "pageAdmin";
	}

	/**
	 * Delete User By ID
	 * @param id
	 */
	@DeleteMapping("/delete")
	@ResponseBody
	public void deleteUser(@RequestBody AdminAPI adminAPI) {
		adminApi.deleteUser(adminAPI.getId());
	}

	/**
	 * Edit Status User -> active or Unactive
	 * @param id
	 */
	@GetMapping("/editActiveUserA")
    @ResponseBody
    public void edit(@RequestParam Long id) {
        adminApi.editActiveUser(id);
    }

	/**
	 * Change Role By Admin
	 * @param id
	 * @param role
	 */
	@GetMapping("/change-role")
	@ResponseBody
	public void changeRole(@RequestParam Long id,@RequestParam String role) {
		adminApi.editRoleUser(id, role);
	}
}
//class like DTO
class AdminAPI{
	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
