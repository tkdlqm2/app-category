package com.category.brand.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class RemoveBrandResponseDto {

    @NotNull
    private boolean result;
    @Builder
    public RemoveBrandResponseDto(boolean result) {
        this.result = result;
    }
}
