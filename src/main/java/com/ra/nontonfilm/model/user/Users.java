package com.ra.nontonfilm.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users",
        indexes = @Index(name = "idx_user_email", columnList = "email", unique = true))
public class Users {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "username", length = 30)
    private String username;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updateAt;
}
