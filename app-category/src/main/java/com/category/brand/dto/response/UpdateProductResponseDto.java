package com.category.brand.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class UpdateProductResponseDto {

    private boolean result;
    @Builder
    public UpdateProductResponseDto(boolean result) {
        this.result = result;
    }
}
