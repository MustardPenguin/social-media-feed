package com.social.media.feed.post.service.application.rest.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostRequestBody {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 128, message = "Title must be less than 128 characters")
    private String title;
    @NotBlank(message = "Description must not be blank")
    @Size(max = 1024, message = "Description must be less than 1024 characters")
    private String description;
}
