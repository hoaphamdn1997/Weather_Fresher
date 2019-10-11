package com.program.weather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Automatic id
    @Column(name = "role_id")
    private Long roleId;


    @Column(name = "role")
    private String role;


}
