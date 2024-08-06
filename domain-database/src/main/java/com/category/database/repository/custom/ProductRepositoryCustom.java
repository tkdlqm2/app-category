package com.category.database.repository.custom;

import com.category.common.enums.CategoryType;
import com.category.database.entity.product.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryCustom {
    Optional<Product> findLowestPricedProductByCategory(CategoryType categoryType);
    Optional<List<Product>> findCheapestProductsPerCategoryByBrandId(Long brandId);
    Optional<List<Product>> findMinPricedProductsByCategory(CategoryType categoryType);
    Optional<List<Product>> findMaxPricedProductsByCategory(CategoryType categoryType);
}
