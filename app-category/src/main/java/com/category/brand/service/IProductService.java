package com.category.brand.service;

import com.category.brand.dto.request.*;
import com.category.brand.dto.response.*;

public interface IProductService {
    CreateProductResponseDto createProduct(CreateProductRequestDto registerProductRequestDto);
    UpdateProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto);
    RemoveProductResponseDto removeProduct(RemoveProductRequestDto removeProductRequestDto);
    CategoryLowestPriceResponseDto getLowestPricedProductsByCategory();
    CategoryPriceRangeResponseDto getCategoryPriceRange(CategoryPriceRangeRequestDto category);
    GetCategoryResponseDto getCategoryList();
    GetProductByConditionResponseDto getProductByBrand(GetProductByConditionRequestDto getProductByBrandRequestDto);
}
