package com.category.brand.dto.response;

import com.category.common.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class GetCategoryResponseDto {
    @JsonProperty("카테고리")
    @NotNull
    private List<String> categoryTypeList;

    @Builder
    public GetCategoryResponseDto(List<String> categoryTypeList) {
        this.categoryTypeList = categoryTypeList;
    }
}
