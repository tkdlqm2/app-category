package com.category.brand.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class GetBrandResponseDto {
    @JsonProperty("브랜드Id")
    private Long id;
    @JsonProperty("브랜드Key")
    private String brandKey;
    @JsonProperty("브랜드 이름")
    private String brandName;
    @JsonProperty("비고")
    private String remark;
    @Builder
    public GetBrandResponseDto(Long id, String brandKey, String brandName, String remark) {
        this.id = id;
        this.brandKey = brandKey;
        this.brandName = brandName;
        this.remark = remark;
    }
}
