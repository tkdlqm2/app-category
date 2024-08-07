package com.category.brand.dto.response;

import com.category.common.model.LowestPriceDetailResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class LowestPriceResponseDto {
    @JsonProperty("최저가")
    @NotNull
    private List<LowestPriceDetailResponseDto> lowestPrice;
    @Builder
    public LowestPriceResponseDto(List<LowestPriceDetailResponseDto> brandCategoryPriceSummaryList) {
        this.lowestPrice = brandCategoryPriceSummaryList;
    }
}