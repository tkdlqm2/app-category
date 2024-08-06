package com.category.brand.dto.request;

import com.category.common.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class UpdateProductRequestDto {
    @NotNull
    @Schema(required = true, description = "수정할 상품 아이디 값", example = "1")
    private Long id;
    @NotNull
    @Schema(required = true, description = "카테고리", example = "HAT")
    private String categoryType;
    @NotNull
    @PositiveOrZero(message = "Price must be zero or positive")
    private BigDecimal price;
    @Schema(required = false, description = "비고", example = "")
    @JsonProperty("remark")
    @Size(max = 100)
    private String remark;
    @Builder
    public UpdateProductRequestDto(String categoryType, BigDecimal price, String remark) {
        this.categoryType = categoryType;
        this.price = price;
        this.remark = remark;
    }
}
