package com.program.weather.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;


    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$", message = "EX: Username123 Only have character a-z, A-Z, 0-9 and Length 8 - 32 char")
    @Column(name = "user_name")
    @NotEmpty(message = "Please provide your user name")
    private String userName;


    @Column(name = "email")
    @Pattern(regexp = "[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}", message = "Email invalid EX:user@mail.com")
    @NotEmpty(message = "Please provide your email")
    private String email;


    @Column(name = "encryted_password")
    @NotEmpty(message = "Please provide your password")
    @Length(min = 8, message = "Min 8 Character")
    private String encrytedPassword;


    @Column(name = "firstname")
    @NotEmpty(message = "Please provide your first name")
    private String firstName;


    @Column(name = "lastname")
    @NotEmpty(message = "Please provide your Last name")
    private String lastName;


    @Column(name = "enabled")
    private boolean enabled;


    @Column(name = "create_date")
    private Timestamp createDate;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

}
