package com.category.brand.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class RemoveProductResponseDto {
    private boolean result;
    @Builder
    public RemoveProductResponseDto(boolean result) {
        this.result = result;
    }
}
