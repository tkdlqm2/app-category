package com.category.brand.service.impl;

import com.category.brand.dto.request.CreateProductRequestDto;
import com.category.brand.dto.request.UpdateProductRequestDto;
import com.category.brand.exception.brand.BrandErrorCode;
import com.category.brand.exception.brand.BrandException;
import com.category.brand.exception.product.ProductErrorCode;
import com.category.brand.exception.product.ProductException;
import com.category.common.enums.CategoryType;
import com.category.common.model.CategoryPriceRangeDetailResponseDto;
import com.category.common.model.CategoryProductDetailDto;
import com.category.database.entity.brand.Brand;
import com.category.database.entity.product.Product;
import com.category.database.repository.BrandRepository;
import com.category.database.repository.ProductRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductServiceHelper {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorCode.NOT_FOUND_PRODUCT_ID));
    }
    public Brand findBrandById(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandException(BrandErrorCode.NOT_FOUND_BRAND_ID));
    }

    public Product createProduct(CreateProductRequestDto createProductRequestDto, Brand brand) {
        Product product = Product.builder()
                .brand(brand)
                .productKey(createProductRequestDto.getProductKey())
                .price(createProductRequestDto.getPrice())
                .categoryType(validateCategoryType(createProductRequestDto.getCategoryType()))
                .build();
        return productRepository.save(product);
    }

    public void updateProductDetails(Product product, UpdateProductRequestDto updateProductRequestDto) {
        CategoryType validatedCategoryType = validateCategoryType(updateProductRequestDto.getCategoryType());
        product.updateProduct(
                updateProductRequestDto.getPrice(),
                validatedCategoryType,
                updateProductRequestDto.getRemark()
        );
    }

    public void validateProductExistsAndDeleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductException(ProductErrorCode.NOT_FOUND_PRODUCT_ID);
        }
        productRepository.deleteById(productId);
    }

    public CategoryProductDetailDto convertToCategoryProduct(Product product) {
        return CategoryProductDetailDto.builder()
                .price(product.getPrice())
                .categoryType(product.getCategoryType())
                .brandName(product.getBrand().getBrandName())
                .build();
    }


    /**
     * 모든 카테고리에 대해 가장 낮은 가격의 제품 목록을 조회합니다.
     *
     * 이 메서드는 다음과 같은 과정을 거칩니다:
     * 1. 모든 CategoryType 값을 순회합니다.
     * 2. 각 카테고리에 대해 가장 낮은 가격의 제품을 조회합니다.
     * 3. 조회된 제품이 없으면 ProductException을 발생시킵니다.
     * 4. 모든 카테고리의 최저가 제품을 리스트로 수집합니다.
     *
     * @return 각 카테고리별 최저가 제품 리스트
     * @throws ProductException 특정 카테고리에 대한 제품을 찾을 수 없을 때 발생 (ProductErrorCode.NOT_FOUND_PRODUCT_CATEGORY)
     */
    public List<Product> getCategoryProductList() {
        return Arrays.stream(CategoryType.values())
                .map(categoryType -> productRepository.findLowestPricedProductByCategory(categoryType)
                        .orElseThrow(() -> new ProductException(ProductErrorCode.NOT_FOUND_PRODUCT_CATEGORY)))
                .collect(Collectors.toList());
    }

    /**
     * 특정 카테고리에 대해 최저가 또는 최고가 제품 목록을 조회하고, 해당 정보를 SubCategoryPriceRange 객체 리스트로 변환합니다.
     *
     * 이 메서드는 다음과 같은 과정을 거칩니다:
     * 1. 주어진 카테고리와 가격 조건(최저가/최고가)에 따라 제품 목록을 조회합니다.
     * 2. 조회된 제품이 없으면 ProductException을 발생시킵니다.
     * 3. 각 제품을 SubCategoryPriceRange 객체로 변환합니다.
     * 4. 변환된 객체들을 리스트로 수집합니다.
     *
     * @param categoryType 조회할 제품 카테고리
     * @param isMinPrice true일 경우 최저가, false일 경우 최고가 제품을 조회
     * @return 해당 카테고리의 최저가 또는 최고가 제품 정보를 담은 SubCategoryPriceRange 객체 리스트
     * @throws ProductException 해당 카테고리에 대한 제품을 찾을 수 없을 때 발생 (ProductErrorCode.NOT_FOUND_PRODUCT_CATEGORY)
     */
    public List<CategoryPriceRangeDetailResponseDto> getPricedProductsByCategory(CategoryType categoryType, boolean isMinPrice) {
        Optional<List<Product>> products = isMinPrice
                ? productRepository.findMinPricedProductsByCategory(categoryType)
                : productRepository.findMaxPricedProductsByCategory(categoryType);

        return products
                .orElseThrow(() -> new ProductException(ProductErrorCode.NOT_FOUND_PRODUCT_CATEGORY))
                .stream()
                .map(product -> CategoryPriceRangeDetailResponseDto.builder()
                        .brandName(product.getBrand().getBrandName())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    public CategoryType validateCategoryType(String category) {
        try {
            return CategoryType.fromDescription(category);
        } catch (IllegalArgumentException e) {
            throw new ProductException(ProductErrorCode.NOT_FOUND_CATEGORYTYPE);
        }
    }

    public List<Product> getProducts(Long brandId, String categoryType) {
        if (brandId == null && StringUtils.isBlank(categoryType)) {
            throw new ProductException(ProductErrorCode.NOT_FOUND_CONDITION);
        }
        return productRepository.getProducts(brandId, CategoryType.fromDescription(categoryType))
                .orElseThrow(() -> new ProductException(ProductErrorCode.NOT_FOUND_PRODUCT_ID));
    }
}
