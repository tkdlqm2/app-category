package com.category.brand.service.impl;

import com.category.brand.dto.request.CreateBrandRequestDto;
import com.category.brand.dto.request.UpdateBrandRequestDto;
import com.category.brand.dto.response.GetBrandResponseDto;
import com.category.brand.exception.brand.BrandErrorCode;
import com.category.brand.exception.brand.BrandException;
import com.category.brand.exception.product.ProductErrorCode;
import com.category.common.enums.CategoryType;
import com.category.common.model.BrandCategoryPriceSummary;
import com.category.common.model.SubBrandCategoryPriceSummaryDto;
import com.category.database.entity.brand.Brand;
import com.category.database.entity.product.Product;
import com.category.database.repository.BrandRepository;
import com.category.database.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BrandServiceHelper {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    /**
     * 모든 카테고리를 가진 브랜드들을 찾습니다.
     *
     * 이 메서드는 다음과 같은 과정을 거칩니다:
     * 1. 모든 브랜드를 조회합니다.
     * 2. 각 브랜드에 대해, 해당 브랜드가 가진 고유한 카테고리의 수를 확인합니다.
     * 3. 카테고리 수가 전체 카테고리 타입의 수와 일치하는 브랜드만 필터링합니다.
     * 4. 조건을 만족하는 브랜드가 없으면 예외를 던집니다.
     *
     * @return 모든 카테고리를 가진 브랜드 리스트
     * @throws BrandException 모든 카테고리를 가진 브랜드가 없을 경우 발생
     */
    public List<Brand> findBrandsWithAllCategories() {
        List<Brand> brandsWithAllCategories = brandRepository.findAll().stream()
                .filter(brand -> brandRepository.countDistinctCategoriesByBrandId(brand.getId()) == CategoryType.values().length)
                .collect(Collectors.toList());

        if (brandsWithAllCategories.isEmpty()) {
            throw new BrandException(BrandErrorCode.NOT_FOUND_ALL_CATEGORY_BRAND);
        }

        return brandsWithAllCategories;
    }


    /**
     * 특정 브랜드에 대한 카테고리별 가격 요약을 생성합니다.
     *
     * 이 메서드는 다음과 같은 과정을 거칩니다:
     * 1. 주어진 브랜드 ID로 각 카테고리별 가장 저렴한 제품들을 조회합니다.
     * 2. 조회된 제품들의 총 가격을 계산합니다.
     * 3. 각 제품을 SubBrandCategoryPriceSummaryDto로 변환합니다. (SubBrandCategoryPriceSummaryDto는 카테고리 정보)
     * 4. 브랜드명, 총 가격, 그리고 카테고리별 요약 정보를 포함한 BrandCategoryPriceSummary 객체를 생성합니다.
     *
     * @param brand 가격 요약을 생성할 브랜드 객체
     * @return 생성된 BrandCategoryPriceSummary 객체
     * @throws BrandException 브랜드에 해당하는 제품이 없을 경우 발생 (ProductErrorCode.NOT_FOUND_PRODUCT_ID)
     */
    public BrandCategoryPriceSummary createBrandCategoryPriceSummary(Brand brand) {
        List<Product> cheapestProducts = productRepository.findCheapestProductsPerCategoryByBrandId(brand.getId())
                .orElseThrow(() -> new BrandException(ProductErrorCode.NOT_FOUND_PRODUCT_ID));
        BigDecimal totalPrice = cheapestProducts.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<SubBrandCategoryPriceSummaryDto> subSummaries = cheapestProducts.stream()
                .map(Product::toDomain)
                .collect(Collectors.toList());

        return BrandCategoryPriceSummary.builder()
                .subBrandCategoryPriceSummaryDtoList(subSummaries)
                .totalPrice(totalPrice)
                .brandName(brand.getBrandName())
                .build();
    }



    /**
     * 주어진 브랜드 카테고리 가격 요약 목록에서 최저 가격과 일치하는 항목들만 필터링합니다.
     *
     * 이 메서드는 다음과 같은 과정을 거칩니다:
     * 1. 입력받은 BrandCategoryPriceSummary 목록을 스트림으로 변환합니다.
     * 2. 각 총 가격과 최저 가격(lowestPrice)과 정확히 일치하는지 확인합니다. (2개 이상의 경우 고려)
     * 3. 조건을 만족하는 요약들만 새 리스트로 수집합니다.
     *
     * @param summaries 필터링할 BrandCategoryPriceSummary 객체들의 리스트
     * @param lowestPrice 필터링 기준이 되는 최저 가격
     * @return 총 가격이 주어진 최저 가격과 일치하는 BrandCategoryPriceSummary 객체들의 리스트
     */
    public List<BrandCategoryPriceSummary> filterBrandsByLowestPrice(List<BrandCategoryPriceSummary> summaries, BigDecimal lowestPrice) {
        return summaries.stream()
                .filter(summary -> summary.getTotalPrice().compareTo(lowestPrice) == 0)
                .collect(Collectors.toList());
    }

    public Brand findBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandException(BrandErrorCode.NOT_FOUND_BRAND_ID));
    }

    public void validateBrandUniquenessAndUpdateBrandDetails(Brand brand, UpdateBrandRequestDto updateBrandRequestDto) {
        if (!brandRepository.existsByBrandKeyAndBrandName(brand.getBrandKey(), brand.getBrandName())) {
            throw new BrandException(BrandErrorCode.DUPLICATE_BRAND);
        }
        brand.updateBrand(
                updateBrandRequestDto.getBrandName(),
                updateBrandRequestDto.getSaleStateCode(),
                updateBrandRequestDto.getRemark()
        );
    }

    public void validateBrandExistsAndDeleteBrand(Long brandId) {
        if (!brandRepository.existsById(brandId)) {
            throw new BrandException(BrandErrorCode.NOT_FOUND_BRAND_ID);
        }
        brandRepository.deleteById(brandId);
    }
    /**
     * 브랜드의 고유성을 검증하고 새로운 브랜드를 생성합니다.
     *
     * 이 메서드는 다음과 같은 과정을 거칩니다:
     * 1. 주어진 브랜드 키와 브랜드 이름의 조합이 이미 존재하는지 확인합니다. (유니크 제약조건)
     * 2. 이미 존재하는 경우, BrandException을 발생시킵니다.
     * 3. 중복이 없는 경우, 새로운 Brand 객체를 생성합니다.
     * 4. 생성된 Brand 객체를 데이터베이스에 저장합니다.
     * @param createBrandRequestDto 생성할 브랜드의 정보를 담고 있는 DTO
     * @return 생성되고 저장된 Brand 객체
     * @throws BrandException 브랜드 키와 이름의 조합이 이미 존재할 경우 발생 (BrandErrorCode.DUPLICATE_BRAND_KEY)
     */
    public Brand validateBrandUniquenessAndCreateBrand(CreateBrandRequestDto createBrandRequestDto) {
        if (brandRepository.existsByBrandKeyAndBrandName(createBrandRequestDto.getBrandKey(), createBrandRequestDto.getBrandName())) {
            throw new BrandException(BrandErrorCode.DUPLICATE_BRAND_KEY);
        }

        Brand brand = Brand.builder()
                .brandName(createBrandRequestDto.getBrandName())
                .brandkey(createBrandRequestDto.getBrandKey())
                .saleStateCode(createBrandRequestDto.getSaleStateCode())
                .build();
        return brandRepository.save(brand);
    }

    public List<Brand> findAllBrand() {
        List<Brand> brands = brandRepository.findAll();
        return  Optional.of(brands)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BrandException(BrandErrorCode.NOT_FOUND_BRAND_ID));
    }

    public GetBrandResponseDto convertToDto(Brand brand) {
        return GetBrandResponseDto.builder()
                .id(brand.getId())
                .brandKey(brand.getBrandKey())
                .brandName(brand.getBrandName())
                .remark(brand.getRemark())
                .build();
    }

}
