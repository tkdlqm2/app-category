package com.category.brand.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CreateProductResponseDto {
    @NotNull
    private Long productId;
    @Builder
    public CreateProductResponseDto(Long productId) {
        this.productId = productId;
    }
}
