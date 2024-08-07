package com.category.common.model;

import com.category.common.enums.CategoryType;
import com.category.common.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandCategoryPriceSummaryDto {
    @JsonProperty("카테고리")
    private String categoryType;
    @JsonProperty("가격")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal price;

    @Builder
    public BrandCategoryPriceSummaryDto(String categoryType, BigDecimal price) {
        this.categoryType = categoryType;
        this.price = price;
    }
}
