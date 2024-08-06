package com.category.brand.controller;

import com.category.brand.dto.request.*;
import com.category.brand.dto.response.*;
import com.category.brand.service.IProductService;
import com.category.common.dto.ResponseDTO;
import com.category.common.utils.CommonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "product API", description = "상품 API")
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @Operation(summary = "상품 등록", description = "상품을 등록하는 API")
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<CreateProductResponseDto>> registerProduct(@RequestBody @Valid CreateProductRequestDto registerProductRequestDto) {
        CreateProductResponseDto registerProductResponse = productService.createProduct(registerProductRequestDto);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "상품 등록성공", registerProductResponse));
    }

    @Operation(summary = "전체 카테고리 조회", description = "전체 카테고리 조회하는 API")
    @GetMapping("/category/list")
    public ResponseEntity<ResponseDTO<GetCategoryResponseDto>> getCategoryList() {
        GetCategoryResponseDto getCategoryResponseDto = productService.getCategoryList();
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "전체 카테고리 조회 성공", getCategoryResponseDto));
    }

    @Operation(summary = "상품 수정", description = "상품을 수정하는 API.")
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<UpdateProductResponseDto>> updateBrand(@RequestBody @Valid UpdateProductRequestDto updateProductRequestDto) {
        UpdateProductResponseDto updateProductResponseDto  = productService.updateProduct(updateProductRequestDto);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "상품 정보 수정성공", updateProductResponseDto));
    }

    @Operation(summary = "상품 삭제", description = "상품을 삭제하는 API.")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO<RemoveProductResponseDto>> updateBrand(@RequestBody @Valid RemoveProductRequestDto removeProductRequestDto) {
        RemoveProductResponseDto removeProductResponseDto = productService.removeProduct(removeProductRequestDto);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "브랜드  삭제성공", removeProductResponseDto));
    }

    @Operation(summary = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회", description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API")
    @GetMapping("/category-min-price")
    public ResponseEntity<ResponseDTO<CategoryLowestPriceResponseDto>> getLowestPricedProductsByCategory() {
        CategoryLowestPriceResponseDto categoryLowestPriceResponseDto = productService.getLowestPricedProductsByCategory();
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "최저가 카테고리 모음 호출 성공", categoryLowestPriceResponseDto));
    }

    @Operation(summary = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회", description = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API")
    @PostMapping("/price-range")
    public ResponseEntity<ResponseDTO<CategoryPriceRangeResponseDto>> getCategoryPriceRange(@RequestBody @Valid CategoryPriceRangeRequestDto categoryPriceRangeRequestDto) {
        CategoryPriceRangeResponseDto categoryPriceRangeResponse = productService.getCategoryPriceRange(categoryPriceRangeRequestDto);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회 호출 성공", categoryPriceRangeResponse));
    }
    @Operation(summary = "특정 조건의 상품 조회", description = "특정 조건의 상품을 조회하는 API")
    @PostMapping("/list/specific")
    public ResponseEntity<ResponseDTO<GetProductByConditionResponseDto>> getProductsByBrand(@RequestBody @Valid GetProductByConditionRequestDto getProductByConditionRequestDto) {
        GetProductByConditionResponseDto getProductByConditionResponseDto = productService.getProductByBrand(getProductByConditionRequestDto);
        return ResponseEntity.ok(CommonUtils.createSuccessResponse(200, "특정 브랜드의 상품을 조회 호출 성공", getProductByConditionResponseDto));
    }
}
