package com.category.brand.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class GetProductByConditionRequestDto {
    @Schema(required = true, description = "브랜드 Id", example = "1")
    private Long brandId;
    @Schema(required = true, description = "category", example = "모자")
    @Size(max=30)
    private String category;
}
