package com.category.brand.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class RemoveProductRequestDto {
    @NotNull
    @Schema(required = true, description = "삭제할 상품 아이디값", example = "1")
    private Long id;
}
