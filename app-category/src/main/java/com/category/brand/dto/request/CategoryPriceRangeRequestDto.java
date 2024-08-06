package com.category.brand.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CategoryPriceRangeRequestDto {
    @NotNull
    @Schema(required = true, description = "카테고리", example = "모자")
    @Size(max = 30)
    private String categoryType;

}
