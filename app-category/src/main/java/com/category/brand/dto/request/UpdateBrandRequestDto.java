package com.category.brand.dto.request;

import com.category.common.enums.SaleStateCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class UpdateBrandRequestDto {
    @NotNull
    @Schema(required = true, description = "수정할 브랜드 아이디값", example = "1")
    private Long id;
    @Schema(description = "수정할 판매 코드", example = "SALE")
    private SaleStateCode saleStateCode;
    @Schema(description = "수정할 브랜드 이름", example = "MUSINSA")
    private String brandName;
    @Schema(description = "수정할 브랜드 내용", example = "remark value")
    private String remark;
    @Builder
    public UpdateBrandRequestDto(Long id, SaleStateCode saleStateCode, String brandName, String remark) {
        this.id = id;
        this.saleStateCode = saleStateCode;
        this.brandName = brandName;
        this.remark = remark;
    }
}
