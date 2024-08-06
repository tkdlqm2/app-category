package com.category.database.entity.brand;

import com.category.common.enums.SaleStateCode;
import com.category.database.entity.BaseEntity;
import com.category.database.entity.product.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import java.util.ArrayList;
import java.util.List;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"brandKey", "brandName"}))
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", nullable = false, updatable = false, columnDefinition = "INT NOT NULL")
    private Long id;
    @Comment("브랜드 키값")
    @Column(name = "brand_key", length = 30, nullable = false, updatable = false, columnDefinition = "VARCHAR(30) NOT NULL")
    private String brandKey;
    @Comment("브랜드 이름")
    @Column(name = "brand_name", length = 30, columnDefinition = "VARCHAR(30) NOT NULL", nullable = false)
    private String brandName;
    @Comment("서비스 여부 코드 SALE : 판매중, UNSALE : 폐지")
    @Column(name = "sale_state_code", length = 30, columnDefinition = "VARCHAR(30) NOT NULL", nullable = false)
    @Enumerated(EnumType.STRING)
    private SaleStateCode saleStateCode;
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();
    @Comment("비고")
    @Column(length = 200)
    private String remark;

    public void updateBrand(String newBrandName, SaleStateCode newSaleStateCode, String newRemark) {
        if (newBrandName != null && !newBrandName.trim().isEmpty()) {
            this.brandName = newBrandName;
        }
        if (newSaleStateCode != null) {
            this.saleStateCode = newSaleStateCode;
        }
        if (newRemark != null) {
            this.remark = newRemark;
        }
    }

    @Builder
    public Brand(Long id, String brandkey, String brandName, SaleStateCode saleStateCode, List<Product> productList, String remark) {
        this.id = id;
        this.brandKey = brandkey;
        this.brandName = brandName;
        this.saleStateCode = saleStateCode;
        this.productList = productList;
        this.remark = remark;
    }
}
