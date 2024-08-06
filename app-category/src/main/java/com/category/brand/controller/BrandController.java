package com.category.brand.controller;

import com.category.brand.dto.request.CreateBrandRequestDto;
import com.category.brand.dto.request.RemoveBrandRequestDto;
import com.category.brand.dto.request.UpdateBrandRequestDto;
import com.category.brand.dto.response.*;
import com.category.brand.service.IBrandService;
import com.category.common.dto.ResponseDTO;
import com.category.common.utils.CommonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "brand API", description = "브랜드 API")
@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {
    private final IBrandService brandService;

    @Operation(summary = "브랜드 등록", description = "브랜드를 생성하는 API")
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<CreateBrandResponseDto>> createBrand(@RequestBody @Valid CreateBrandRequestDto createBrandRequestDto) {
        CreateBrandResponseDto createBrandResponse = brandService.createBrand(createBrandRequestDto);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "브랜드 생성성공", createBrandResponse));
    }

    @Operation(summary = "브랜드 수정", description = "브랜드를 수정하는 API.")
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<UpdateBrandResponseDto>> updateBrand(@RequestBody @Valid UpdateBrandRequestDto updateBrandRequestDto) {
        UpdateBrandResponseDto updateBrandResponseDto = brandService.updateBrand(updateBrandRequestDto);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "브랜드 정보 수정성공", updateBrandResponseDto));
    }
    @Operation(summary = "브랜드 삭제", description = "브랜드를 삭제하는 API.")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<RemoveBrandResponseDto>> updateBrand(@RequestBody @Valid RemoveBrandRequestDto removeBrandRequestDto) {
        RemoveBrandResponseDto removeBrandResponseDto = brandService.removeBrand(removeBrandRequestDto);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "브랜드  삭제성공", removeBrandResponseDto));
    }

    @Operation(summary = "제일 저렴한 카테고리 셋트를 제공하는 브랜드와 카테고리 상품 조회", description = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API")
    @GetMapping("/lowest-price-brands")
    public ResponseEntity<ResponseDTO<LowestPriceResponseDto>> findLowestPriceForBrand() {
        LowestPriceResponseDto lowestPrice = brandService.findLowestPriceForBrand();
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "제일 저렴한 카테고리 셋트를 제공하는 브랜드와 카테고리 상품 조회 호출 성공", lowestPrice));
    }

    @Operation(summary = "전체 브랜드 조회", description = "전체 브랜드 조회하는 API")
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO<List<GetBrandResponseDto>>> getBrandList() {
        List<GetBrandResponseDto> getBrandListResponseList = brandService.getBrandList();
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "전체 브랜드 조회 성공", getBrandListResponseList));
    }
    @Operation(
            summary = "단일 브랜드 조회",
            description = "단일 브랜드 조회하는 API",
            parameters = {
                    @Parameter(name = "brandId", description = "브랜드 ID", required = true)
            }
    )
    @GetMapping("/brands/{brandId}")
    public ResponseEntity<ResponseDTO<GetBrandResponseDto>> getBrand(
            @PathVariable("brandId") @Parameter(description = "브랜드 ID") Long brandId) {
        GetBrandResponseDto getBrandResponse = brandService.getBrand(brandId);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "단일 브랜드 조회 성공", getBrandResponse));
    }
}
