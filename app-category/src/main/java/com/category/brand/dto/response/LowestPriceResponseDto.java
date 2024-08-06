package com.category.brand.dto.response;

import com.category.common.model.BrandCategoryPriceSummary;
import com.category.common.model.SubBrandCategoryPriceSummaryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class LowestPriceResponseDto {
    @JsonProperty("최저가")
    @NotNull
    private List<BrandCategoryPriceSummary> lowestPrice;
    @Builder
    public LowestPriceResponseDto(List<BrandCategoryPriceSummary> brandCategoryPriceSummaryList) {
        this.lowestPrice = brandCategoryPriceSummaryList;
    }
}