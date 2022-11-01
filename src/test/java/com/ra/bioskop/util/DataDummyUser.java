package com.ra.bioskop.util;

import com.ra.bioskop.model.user.ERoles;
import com.ra.bioskop.model.user.Roles;
import com.ra.bioskop.model.user.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataDummyUser {

    private BCryptPasswordEncoder encoder;
    private final List<Roles> ROLES = new ArrayList<>(
            List.of(
                    new Roles(1, ERoles.ROLE_ADMIN, new ArrayList<>()),
                    new Roles(2, ERoles.ROLE_CUSTOMER, new ArrayList<>())
            ));
    private final List<Users> USERS = new ArrayList<>();

    public DataDummyUser() {
        encoder = new BCryptPasswordEncoder();

        Users user1 = new Users();
        user1.setId("customer-001");
        user1.setUsername("sheila");
        user1.setPassword(encoder.encode("sheila007"));
        user1.setEmail("sheila@gmail.com");
        user1.setUpdatedAt(LocalDateTime.now());
        user1.setCreatedAt(LocalDateTime.now());

        Users user2 = new Users();
        user2.setId("customer-002");
        user2.setUsername("sophia");
        user2.setPassword(encoder.encode("sophia77"));
        user2.setEmail("sophia@gmail.com");
        user2.setUpdatedAt(LocalDateTime.now());
        user2.setCreatedAt(LocalDateTime.now());

        Users user3 = new Users();
        user3.setId("admin-001");
        user3.setUsername("riswan");
        user3.setPassword(encoder.encode("riswan99"));
        user3.setEmail("riswan@gmail.com");
        user3.setUpdatedAt(LocalDateTime.now());
        user3.setCreatedAt(LocalDateTime.now());

        user1.setRoles(ROLES);
        user2.setRoles(ROLES);
        user3.setRoles(ROLES);
    }


    public List<Users> getAllUser() {
        return USERS;
    }

    public List<Roles> getRoles() {
        return ROLES;
    }

    public Optional<Users> findById(String id) {
        return USERS.stream()
                .filter(users -> users.getId().equals(id))
                .findFirst();
    }
}
