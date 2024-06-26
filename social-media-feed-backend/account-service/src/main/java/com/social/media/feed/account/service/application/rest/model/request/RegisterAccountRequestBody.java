package com.social.media.feed.account.service.application.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAccountRequestBody {

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message="Username must contain only alphanumeric characters and underscores")
    private String username;
    @NotBlank(message = "Password is required")
    @Size(max = 50, message = "Password must be less than 50 characters")
    private String password;
}
