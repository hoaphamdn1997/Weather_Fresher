package com.program.weather.entity;

import lombok.Data;
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
import java.util.List;
import java.util.Set;

/**
 * The type User entity.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "encryted_password")
    private String encrytedPassword;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "create_date")
    private Timestamp createDate;

    /**
     * Instantiates a new User entity.
     *
     * @param userName         the user name
     * @param email            the email
     * @param encrytedPassword the encryted password
     * @param firstName        the first name
     * @param lastName         the last name
     */
    public UserEntity(String userName, String email, String encrytedPassword, String firstName, String lastName) {
        this.userName = userName;
        this.email = email;
        this.encrytedPassword = encrytedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
}
