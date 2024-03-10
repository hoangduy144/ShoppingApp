package com.project.shopapp.responses;

import lombok.*;

import java.util.List;

@Data//toString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponses {
    private List<ProductResponse> products;
    private int totalPages;
}
