package com.category.brand.dto.response;

import com.category.common.model.CategoryProductDetailDto;
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
public class CategoryLowestPriceResponseDto {
    @NotNull
    List<CategoryProductDetailDto> categoryProducts;
    @NotNull
    @JsonProperty("총액")
    @JsonSerialize(using = BigDecimalSerializer.class)
    BigDecimal totalPrice;

    @Builder
    public CategoryLowestPriceResponseDto(List<CategoryProductDetailDto> categoryProducts, BigDecimal totalPrice) {
        this.categoryProducts = categoryProducts;
        this.totalPrice = totalPrice;
    }
}
