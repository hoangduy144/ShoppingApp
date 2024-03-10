package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateCategoryResponse {
    @JsonProperty("message")
    private String message;
}

