package com.ra.nontonfilm.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users",
        indexes = @Index(name = "idx_user_email", columnList = "email", unique = true))
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username", length = 30)
    private String username;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 25)
    private String password;

    @Column(name = "created_at")
    private String createdAt;
}
