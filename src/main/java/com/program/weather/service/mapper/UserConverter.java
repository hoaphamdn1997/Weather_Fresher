package com.program.weather.service.mapper;

import com.program.weather.service.dto.UserDTO;
import com.program.weather.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * The type User converter.
 */
@Component
public class UserConverter {
    /**
     * Conver UserDto --> UserEntiTy
     *
     * @param userEntity the user entity
     * @return UserEntity user dto
     */
    public UserDTO convertUserToDTO(UserEntity userEntity) {

        UserDTO user = new UserDTO(userEntity.getUserId(), userEntity.getUserName(),
                userEntity.getEmail(), userEntity.getFirstName(),
                userEntity.getLastName(), userEntity.isEnabled());

        Set<Long> roles = new HashSet<Long>();
        userEntity.getRoles().stream().forEach(i -> {
            roles.add(i.getRoleId());
        });

        user.setRoles(roles);

        return user;
    }

    /**
     * conver UserEntity --> UserDTO
     *
     * @param userDTO the user dto
     * @return user entity
     */
    public UserEntity convertUserEntity(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity(userDTO.getUserName(), userDTO.getEmail(),
                userDTO.getEncrytedPassword(), userDTO.getFirstName(),
                userDTO.getLastName());
        return userEntity;
    }
}
