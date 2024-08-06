package com.category.database.repository.custom.impl;

import com.category.common.enums.CategoryType;
import com.category.database.entity.brand.QBrand;
import com.category.database.entity.product.Product;
import com.category.database.entity.product.QProduct;
import com.category.database.repository.custom.ProductRepositoryCustom;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QProduct product = QProduct.product;
    private final QBrand brand = QBrand.brand;

    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Product> findLowestPricedProductByCategory(CategoryType categoryType) {
        return Optional.ofNullable(queryFactory
                .selectFrom(product)
                .join(product.brand, brand)
                .where(product.categoryType.eq(categoryType)
                        .and(product.price.eq(
                                JPAExpressions
                                        .select(product.price.min())
                                        .from(product)
                                        .where(product.categoryType.eq(categoryType))
                        )))
                .orderBy(product.categoryType.asc())
                .fetchFirst());
    }

    @Override
    public Optional<List<Product>> findCheapestProductsPerCategoryByBrandId(Long brandId) {
        List<Product> result = queryFactory
                .selectFrom(product)
                .join(product.brand, brand).fetchJoin()
                .where(product.brand.id.eq(brandId)
                        .and(product.price.in(
                                JPAExpressions
                                        .select(product.price.min())
                                        .from(product)
                                        .where(product.brand.id.eq(brandId))
                                        .groupBy(product.categoryType)
                        )))
                .orderBy(product.categoryType.asc())
                .fetch();

        return Optional.ofNullable(result.isEmpty() ? null : result);
    }

    @Override
    public Optional<List<Product>> findMinPricedProductsByCategory(CategoryType categoryType) {
        List<Product> result = queryFactory
                .selectFrom(product)
                .join(product.brand, brand).fetchJoin()
                .where(product.categoryType.eq(categoryType)
                        .and(product.price.eq(
                                JPAExpressions
                                        .select(product.price.min())
                                        .from(product)
                                        .where(product.categoryType.eq(categoryType))
                        )))
                .fetch();

        return Optional.ofNullable(result.isEmpty() ? null : result);
    }

    @Override
    public Optional<List<Product>> findMaxPricedProductsByCategory(CategoryType categoryType) {
        List<Product> result = queryFactory
                .selectFrom(product)
                .join(product.brand, brand).fetchJoin()
                .where(product.categoryType.eq(categoryType)
                        .and(product.price.eq(
                                JPAExpressions
                                        .select(product.price.max())
                                        .from(product)
                                        .where(product.categoryType.eq(categoryType))
                        )))
                .fetch();

        return Optional.ofNullable(result.isEmpty() ? null : result);
    }
}
