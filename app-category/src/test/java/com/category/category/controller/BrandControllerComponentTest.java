package com.category.category.controller;

import com.category.brand.controller.BrandController;
import com.category.brand.dto.response.LowestPriceResponseDto;
import com.category.brand.service.impl.BrandServiceImpl;
import com.category.category.common.util.TestDataGenerator;
import com.category.common.serializer.BigDecimalSerializer;
import com.category.database.entity.brand.Brand;
import com.category.database.entity.product.Product;
import com.category.database.repository.BrandRepository;
import com.category.database.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@WebMvcTest(BrandController.class)
@Import({BigDecimalSerializer.class})
public class BrandControllerComponentTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandServiceImpl brandService;

    @MockBean
    private BrandRepository brandRepository;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    @Transactional
    void setup() {
        List<Brand> brandList = TestDataGenerator.generateBrand();
        List<Product> productList = TestDataGenerator.generateProducts(brandList);
        for(Brand brand : brandList) {
            brandRepository.save(brand);
        }
        for(Product product : productList) {
            productRepository.save(product);
        }
    }
    @Test
    void findLowestPriceForBrand() throws Exception {

        // Given
//        LowestPriceResponseDto

    }
}
