package com.ra.bioskop.dto.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ra.bioskop.util.EmailFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String username;

    @EmailFormat
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
