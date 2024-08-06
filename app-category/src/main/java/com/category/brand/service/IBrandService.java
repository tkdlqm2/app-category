package com.category.brand.service;

import com.category.brand.dto.request.CreateBrandRequestDto;
import com.category.brand.dto.request.RemoveBrandRequestDto;
import com.category.brand.dto.request.UpdateBrandRequestDto;
import com.category.brand.dto.response.*;

import java.util.List;

public interface IBrandService {

    CreateBrandResponseDto createBrand(CreateBrandRequestDto createBrandRequestDto);
    RemoveBrandResponseDto removeBrand(RemoveBrandRequestDto removeBrandRequestDto);
    UpdateBrandResponseDto updateBrand(UpdateBrandRequestDto updateBrandRequestDto);
    LowestPriceResponseDto findLowestPriceForBrand();
    List<GetBrandResponseDto> getBrandList();
    GetBrandResponseDto getBrand(Long brandId);

}
