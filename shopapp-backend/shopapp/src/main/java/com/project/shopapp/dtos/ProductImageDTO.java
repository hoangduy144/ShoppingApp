package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data//toString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {

    @Min(value = 1, message = "Product's id must be >=1")
    @JsonProperty("product_id")
    private Long productId;

    @Size (min=5, max=200, message = "Image's name must be between 3 and 200 characters")
    @JsonProperty("image_url")
    private String imageUrl;
}
