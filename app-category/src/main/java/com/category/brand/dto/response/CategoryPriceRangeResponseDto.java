package com.category.brand.dto.response;

import com.category.common.enums.CategoryType;
import com.category.common.model.SubCategoryPriceRange;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class CategoryPriceRangeResponseDto {
    @JsonProperty("카테고리")
    private String categoryType;
    @JsonProperty("최저가")
    private List<SubCategoryPriceRange> cheapestPrice;
    @JsonProperty("최고가")
    private List<SubCategoryPriceRange> mostExpensivePrice;

    @Builder
    public CategoryPriceRangeResponseDto(CategoryType categoryType, List<SubCategoryPriceRange> cheapestPrice, List<SubCategoryPriceRange> mostExpensivePrice) {
        this.categoryType = categoryType.getDescription();
        this.cheapestPrice = cheapestPrice;
        this.mostExpensivePrice = mostExpensivePrice;
    }
}
