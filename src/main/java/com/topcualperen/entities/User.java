package com.topcualperen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", unique = true)
    @Email
    @NotBlank(message = "Email may not be blank")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Password may not be blank")
    private String password;
    @Column(name = "user_name")
    private String userName;

}
