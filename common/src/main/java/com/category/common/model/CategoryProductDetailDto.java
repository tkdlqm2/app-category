package com.category.common.model;

import com.category.common.enums.CategoryType;
import com.category.common.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CategoryProductDetailDto {
    @JsonProperty("카테고리")
    private String categoryType;
    @JsonProperty("브랜드")
    private String brandName;
    @JsonProperty("가격")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal price;

    @Builder
    public CategoryProductDetailDto(CategoryType categoryType, String brandName, BigDecimal price) {
        this.categoryType = categoryType.getDescription();
        this.brandName = brandName;
        this.price = price;
    }
}
