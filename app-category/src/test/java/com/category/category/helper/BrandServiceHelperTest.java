package com.category.category.helper;

import com.category.brand.service.impl.BrandServiceHelper;
import com.category.common.enums.CategoryType;
import com.category.common.enums.SaleStateCode;
import com.category.database.entity.brand.Brand;
import com.category.database.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrandServiceHelperTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceHelper brandServiceHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findBrandsWithAllCategories_Success() {
        // Given
        Brand brand1 = Brand.builder()
                .brandName("A")
                .saleStateCode(SaleStateCode.SALE)
                .brandkey("B00009")
                .id(1L)
                .build();

        Brand brand2 = Brand.builder()
                .brandName("B")
                .saleStateCode(SaleStateCode.SALE)
                .brandkey("B00010")
                .id(2L)
                .build();

        List<Brand> allBrands = Arrays.asList(brand1, brand2);

        when(brandRepository.findAll()).thenReturn(allBrands);
        when(brandRepository.countDistinctCategoriesByBrandId(1L)).thenReturn((long) CategoryType.values().length);
        when(brandRepository.countDistinctCategoriesByBrandId(2L)).thenReturn((long) CategoryType.values().length);

        // When
        List<Brand> result = brandServiceHelper.findBrandsWithAllCategories();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(brand1));
        assertTrue(result.contains(brand2));
        verify(brandRepository, times(1)).findAll();
        verify(brandRepository, times(1)).countDistinctCategoriesByBrandId(1L);
        verify(brandRepository, times(1)).countDistinctCategoriesByBrandId(2L);
    }
}
