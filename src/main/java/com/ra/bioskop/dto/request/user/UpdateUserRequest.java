package com.ra.bioskop.dto.request.user;

import com.ra.bioskop.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserRequest {
    @Email
    @Pattern(regexp = Constants.EMAIL_PATTERN)
    private String email;

    @Size(min = 5, max = 20)
    private String newUsername;
}
