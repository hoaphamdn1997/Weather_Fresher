package com.program.weather.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.program.weather.common.api.AdminApi;

import com.program.weather.dto.UserDTO;
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
		List<UserDTO> dsUser = adminApi.findAll().stream().map(AdminController::castUserDTO).collect(Collectors.toList());
		//list Role
		List<RoleEntity> dsRole = adminApi.findAllRole();
		//ATTRIBUTE
		model.addAttribute("dsUser",dsUser);
		model.addAttribute("dsRole", dsRole);
		return "pageAdmin";
	}

	public static UserDTO castUserDTO(UserEntity userEntity){

		UserDTO user =  new UserDTO(userEntity.getUserId(), userEntity.getUserName(), userEntity.getEmail()
				, userEntity.getFirstName(), userEntity.getLastName(), userEntity.isEnabled());

		Set<Long> roles = new HashSet<>();

		userEntity.getRoles().stream().forEach(i->{
			roles.add(i.getRoleId());
		});

		user.setRoles(roles);

		return user;
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
	@PutMapping("/change-role")
	@ResponseBody
	public void changeRole(@RequestBody UserRestDTO userRestDTO) {
		adminApi.editRoleUser(userRestDTO.getId(),userRestDTO.getRole()) ;
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
class UserRestDTO{
	private long id;
	private String role;

	public void setId(long id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}
}

