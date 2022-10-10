package com.ra.bioskop.dto.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginDTO {
    private String userId;
    private String email;
    private String token;
}
