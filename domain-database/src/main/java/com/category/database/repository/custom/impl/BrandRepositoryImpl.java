package com.category.database.repository.custom.impl;

import com.category.database.entity.brand.QBrand;
import com.category.database.entity.product.QProduct;
import com.category.database.repository.custom.BrandRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BrandRepositoryImpl implements BrandRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QBrand brand = QBrand.brand;
    private final QProduct product = QProduct.product;

    @Autowired
    public BrandRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Long countDistinctCategoriesByBrandId(Long brandId) {
        return queryFactory
                .select(product.categoryType.countDistinct())
                .from(brand)
                .join(brand.productList, product)
                .where(brand.id.eq(brandId))
                .fetchOne();
    }
}
