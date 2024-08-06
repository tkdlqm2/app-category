package com.category.brand.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CreateBrandResponseDto {
    @NotNull
    private Long brandId;
    @Builder
    public CreateBrandResponseDto(Long brandId) {
        this.brandId = brandId;
    }
}
