package com.category.brand.service.impl;

import com.category.brand.dto.request.*;
import com.category.brand.dto.response.*;
import com.category.common.enums.CategoryType;
import com.category.brand.service.IProductService;
import com.category.common.model.CategoryProductDetailDto;
import com.category.common.model.GetProductByConditionDetailResponseDto;
import com.category.database.entity.brand.Brand;
import com.category.database.entity.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductServiceHelper productServiceHelper;
    @Override
    @Transactional
    public CreateProductResponseDto createProduct(CreateProductRequestDto createProductRequestDto) {
        Brand brand = productServiceHelper.findBrandById(createProductRequestDto.getBrandId());
        Product product = productServiceHelper.createProduct(createProductRequestDto, brand);
        return CreateProductResponseDto.builder().productId(product.getId()).build();
    }

    @Override
    @Transactional
    public UpdateProductResponseDto updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        Product product = productServiceHelper.findProductById(updateProductRequestDto.getId());
        productServiceHelper.updateProductDetails(product, updateProductRequestDto);
        return UpdateProductResponseDto.builder().result(true).build();
    }

    @Override
    @Transactional
    public RemoveProductResponseDto removeProduct(RemoveProductRequestDto removeProductRequestDto) {
        productServiceHelper.validateProductExistsAndDeleteProduct(removeProductRequestDto.getId());
        return RemoveProductResponseDto.builder().result(true).build();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryLowestPriceResponseDto getLowestPricedProductsByCategory() {
        List<Product> productList = productServiceHelper.getCategoryProductList();
        BigDecimal totalPrice = productList.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        List<CategoryProductDetailDto> categoryProductList = productList.stream()
                .map(productServiceHelper::convertToCategoryProduct)
                .collect(Collectors.toList());
        return CategoryLowestPriceResponseDto.builder()
                .categoryProducts(categoryProductList)
                .totalPrice(totalPrice)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryPriceRangeResponseDto getCategoryPriceRange(CategoryPriceRangeRequestDto category) {
        CategoryType categoryType = productServiceHelper.validateCategoryType(category.getCategoryType());
        return CategoryPriceRangeResponseDto.builder()
                .categoryType(categoryType)
                .cheapestPrice(productServiceHelper.getPricedProductsByCategory(categoryType, true))
                .mostExpensivePrice(productServiceHelper.getPricedProductsByCategory(categoryType, false))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public GetCategoryResponseDto getCategoryList() {
        List<CategoryType> categoryTypeList = Arrays.asList(CategoryType.values());
        List<String> categoryDescriptions = categoryTypeList.stream()
                .map(CategoryType::getDescription)
                .collect(Collectors.toList());

        return GetCategoryResponseDto.builder()
                .categoryTypeList(categoryDescriptions)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public GetProductByConditionResponseDto getProductByBrand(GetProductByConditionRequestDto getProductByBrandRequestDto) {
        List<Product> productList = productServiceHelper.getProducts(getProductByBrandRequestDto.getBrandId(), getProductByBrandRequestDto.getCategory());
        List<GetProductByConditionDetailResponseDto> categoryProducts = productList.stream()
                .map(Product::toDomainCategoryProduct)
                .collect(Collectors.toList());
        return GetProductByConditionResponseDto.builder()
                .productList(categoryProducts)
                .build();
    }
}
