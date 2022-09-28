package com.ra.bioskop.dto.request.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ra.bioskop.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisRequest {

    @NotNull
    @Pattern(regexp = Constants.EMAIL_PATTERN)
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String username;

    @NotNull
    @Size(min = 7, max = 20)
    private String password;
}
