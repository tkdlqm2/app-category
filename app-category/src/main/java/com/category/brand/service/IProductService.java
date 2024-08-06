package com.category.brand.service;

import com.category.brand.dto.request.CategoryPriceRangeRequestDto;
import com.category.brand.dto.request.CreateProductRequestDto;
import com.category.brand.dto.request.RemoveProductRequestDto;
import com.category.brand.dto.request.UpdateProductRequestDto;
import com.category.brand.dto.response.*;

public interface IProductService {
    CreateProductResponseDto createProduct(CreateProductRequestDto registerProductRequestDto);
    UpdateProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto);
    RemoveProductResponseDto removeProduct(RemoveProductRequestDto removeProductRequestDto);
    CategoryLowestPriceResponseDto getLowestPricedProductsByCategory();
    CategoryPriceRangeResponseDto getCategoryPriceRange(CategoryPriceRangeRequestDto category);

}
