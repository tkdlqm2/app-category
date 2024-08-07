package com.category.brand.service.impl;

import com.category.brand.dto.request.CreateBrandRequestDto;
import com.category.brand.dto.request.RemoveBrandRequestDto;
import com.category.brand.dto.request.UpdateBrandRequestDto;
import com.category.brand.dto.response.*;
import com.category.brand.exception.brand.BrandErrorCode;
import com.category.brand.exception.brand.BrandException;
import com.category.brand.service.IBrandService;
import com.category.common.model.LowestPriceDetailResponseDto;
import com.category.database.entity.brand.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {
    private final BrandServiceHelper brandServiceHelper;
    @Override
    @Transactional
    public CreateBrandResponseDto createBrand(CreateBrandRequestDto createBrandRequestDto) {
        Brand brand = brandServiceHelper.validateBrandUniquenessAndCreateBrand(createBrandRequestDto);
        return CreateBrandResponseDto.builder()
                .brandId(brand.getId())
                .build();
    }
    @Override
    @Transactional(readOnly = true)
    public GetBrandResponseDto getBrand(Long brandId) {
        Brand brand = brandServiceHelper.findBrandById(brandId);
        GetBrandResponseDto getBrandResponseDto = GetBrandResponseDto.builder()
                .id(brand.getId())
                .brandKey(brand.getBrandKey())
                .brandName(brand.getBrandName())
                .remark(brand.getRemark())
                .build();
        return getBrandResponseDto;
    }
    @Override
    @Transactional(readOnly = true)
    public List<GetBrandResponseDto> getBrandList() {
        return brandServiceHelper.findAllBrand().stream()
                .map(brandServiceHelper::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public RemoveBrandResponseDto removeBrand(RemoveBrandRequestDto removeBrandRequestDto) {
        Long brandId = removeBrandRequestDto.getId();
        brandServiceHelper.validateBrandExistsAndDeleteBrand(brandId);
        return RemoveBrandResponseDto.builder().result(true).build();
    }

    @Override
    @Transactional
    public UpdateBrandResponseDto updateBrand(UpdateBrandRequestDto updateBrandRequestDto) {
        Brand brand = brandServiceHelper.findBrandById(updateBrandRequestDto.getId());
        brandServiceHelper.validateBrandUniquenessAndUpdateBrandDetails(brand, updateBrandRequestDto);
        return UpdateBrandResponseDto.builder().result(true).build();
    }

    @Override
    @Transactional(readOnly = true)
    public LowestPriceResponseDto findLowestPriceForBrand() {
        List<Brand> brandsWithAllCategories = brandServiceHelper.findBrandsWithAllCategories();
        List<LowestPriceDetailResponseDto> allResults = brandsWithAllCategories.stream()
                .map(brandServiceHelper::createBrandCategoryPriceSummary)
                .collect(Collectors.toList());
        BigDecimal lowestTotalPrice = allResults.stream()
                .map(LowestPriceDetailResponseDto::getTotalPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        return LowestPriceResponseDto.builder()
                .brandCategoryPriceSummaryList(brandServiceHelper.filterBrandsByLowestPrice(allResults, lowestTotalPrice))
                .build();
    }
}
