package com.ra.bioskop.dto.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ra.bioskop.util.EmailFormat;
import com.ra.bioskop.util.PasswordFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisRequest {
    @NotNull
    @EmailFormat
    private String email;

    @NotNull
    @Length(min = 6)
    private String username;

    @NotNull
    @Length(min = 8, max = 15)
    @PasswordFormat
    private String password;
}
