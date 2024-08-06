package com.category.database.repository.custom;

public interface BrandRepositoryCustom {
    Long countDistinctCategoriesByBrandId(Long brandId);
}
