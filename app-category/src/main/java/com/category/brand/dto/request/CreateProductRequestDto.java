package com.category.brand.dto.request;

import com.category.common.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CreateProductRequestDto {
    @PositiveOrZero(message = "Price must be zero or positive")
    @NotNull
    @Schema(required = true, description = "가격", example = "7000")
    private BigDecimal price;
    @Schema(required = true, description = "상품 키", example = "M0000000000000001")
    @NotBlank(message = "productKey 값이 존재하지 않습니다.")
    @Size(max=30)
    @NotNull
    private String productKey;
    @Schema(required = true, description = "브랜드ID", example = "1")
    @NotNull
    private Long brandId;
    @NotNull
    @Schema(required = true, description = "카테고리 정보 - 상의, 하의, 모자, ...", example = "모자")
    private String categoryType;
    @Schema(required = false, description = "비고", example = "")
    @JsonProperty("remark")
    @Size(max = 100)
    private String remark;
}
