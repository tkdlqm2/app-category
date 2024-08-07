package com.category.database.entity.product;

import com.category.common.enums.CategoryType;
import com.category.common.model.BrandCategoryPriceSummaryDto;
import com.category.common.model.GetProductByConditionDetailResponseDto;
import com.category.database.entity.BaseEntity;
import com.category.database.entity.brand.Brand;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {
    @Id
    @Comment("상품Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, updatable = false, columnDefinition = "INT NOT NULL")
    private Long id;

    @Comment("가격")
    @Column(nullable = false, columnDefinition = "DECIMAL(34,18) NOT NULL")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id", nullable = false, updatable = false)
    private Brand brand;

    @Comment("상품키값")
    @Column(length = 30, nullable = false, updatable = false, columnDefinition = "VARCHAR(30) NOT NULL")
    private String productKey;

    @Comment("카테고리 유형 - HAT, BAG, TOP, PANTS")
    @Column(nullable = false, columnDefinition = "VARCHAR(30) NOT NULL")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Comment("비고")
    @Column(length = 200)
    private String remark;

    @Builder
    public Product(BigDecimal price, Brand brand, String productKey, CategoryType categoryType, String remark) {
        validateFields(price, brand, productKey, categoryType);
        this.price = price;
        this.brand = brand;
        this.productKey = productKey;
        this.categoryType = categoryType;
        this.remark = remark;
    }

    private void validateFields(BigDecimal price, Brand brand, String productKey, CategoryType categoryType) {
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        if (brand == null) {
            throw new IllegalArgumentException("Brand cannot be null");
        }
        if (productKey == null || productKey.trim().isEmpty()) {
            throw new IllegalArgumentException("ProductKey cannot be null or empty");
        }
        if (categoryType == null) {
            throw new IllegalArgumentException("CategoryType cannot be null");
        }
    }

    public BrandCategoryPriceSummaryDto toDomainSubBrandCategoryPriceSummaryDto() {
        return BrandCategoryPriceSummaryDto.builder()
                .price(price)
                .categoryType(categoryType.getDescription())
                .build();
    }

    public GetProductByConditionDetailResponseDto toDomainCategoryProduct() {
        return GetProductByConditionDetailResponseDto.builder()
                .id(id)
                .brandName(brand.getBrandName())
                .categoryType(categoryType)
                .price(price)
                .build();
    }

    public void updateProduct(BigDecimal newPrice, CategoryType newCategoryType, String newRemark) {
        if (newPrice != null && newPrice.compareTo(BigDecimal.ZERO) >= 0) {
            this.price = newPrice;
        } else if (newPrice != null) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (newCategoryType != null) {
            this.categoryType = newCategoryType;
        }
        if (newRemark != null) {
            this.remark = newRemark.trim();
        }
    }
}