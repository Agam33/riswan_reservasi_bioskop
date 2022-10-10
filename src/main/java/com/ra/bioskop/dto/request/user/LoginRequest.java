package com.ra.bioskop.dto.request.user;

import com.ra.bioskop.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @Pattern(regexp = Constants.EMAIL_PATTERN)
    private String email;
    @NotNull
    private String password;
}
