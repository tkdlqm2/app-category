package com.category.brand.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class UpdateBrandResponseDto {
    private boolean result;
    @Builder
    public UpdateBrandResponseDto(boolean result) {
        this.result = result;
    }
}
