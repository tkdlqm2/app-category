package com.category.common.model;

import com.category.common.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class BrandCategoryPriceSummary {
    @NotNull
    @JsonProperty("브랜드")
    private String brandName;

    @NotNull
    @JsonProperty("총액")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal totalPrice;

    @NotNull
    @JsonProperty("카테고리")
    private List<SubBrandCategoryPriceSummaryDto> category;

    @Builder
    public BrandCategoryPriceSummary(String brandName, List<SubBrandCategoryPriceSummaryDto> subBrandCategoryPriceSummaryDtoList, BigDecimal totalPrice) {
        this.brandName = brandName;
        this.category = subBrandCategoryPriceSummaryDtoList;
        this.totalPrice = totalPrice;
    }
}
