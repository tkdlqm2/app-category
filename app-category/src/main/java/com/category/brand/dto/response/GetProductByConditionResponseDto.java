package com.category.brand.dto.response;

import com.category.common.model.GetProductByConditionDetailResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class GetProductByConditionResponseDto {
    @JsonProperty("상품 리스트")
    @NotNull
    private List<GetProductByConditionDetailResponseDto> productList;
    @Builder
    public GetProductByConditionResponseDto(List<GetProductByConditionDetailResponseDto> productList) {
        this.productList = productList;
    }
}
