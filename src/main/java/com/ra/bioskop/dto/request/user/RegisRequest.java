package com.ra.bioskop.dto.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ra.bioskop.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisRequest {

    @Email
    @Pattern(regexp = Constants.EMAIL_PATTERN)
    private String email;

    @Size(min = 5, max = 20)
    private String username;

    @Size(min = 7, max = 20)
    private String password;
}
