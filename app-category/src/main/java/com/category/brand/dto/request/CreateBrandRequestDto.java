package com.category.brand.dto.request;

import com.category.common.enums.SaleStateCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CreateBrandRequestDto {
    @Schema(required = true, description = "브랜드 키", example = "B0001")
    @NotBlank(message = "BrandKey 값이 존재하지 않습니다.")
    @Size(max=30)
    private String brandKey;

    @Schema(required = true, description = "브랜드 이름", example = "A")
    @NotBlank(message = "BrandName 값이 존재하지 않습니다.")
    @Size(max=30)
    private String brandName;

    @Schema(required = true, description = "브랜드 상태", example = "SALE")
    @NotNull
    private SaleStateCode saleStateCode;

    @Schema(required = false, description = "비고", example = "")
    @JsonProperty("remark")
    @Size(max = 100)
    private String remark;

    @Builder
    public CreateBrandRequestDto(String brandKey, String brandName, SaleStateCode saleStateCode, String remark) {
        this.brandKey = brandKey;
        this.brandName = brandName;
        this.saleStateCode = saleStateCode;
        this.remark = remark;
    }
}
