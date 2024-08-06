package com.category.database.entity.brand;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBrand is a Querydsl query type for Brand
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBrand extends EntityPathBase<Brand> {

    private static final long serialVersionUID = 930947941L;

    public static final QBrand brand = new QBrand("brand");

    public final com.category.database.entity.QBaseEntity _super = new com.category.database.entity.QBaseEntity(this);

    public final StringPath brandkey = createString("brandkey");

    public final StringPath brandName = createString("brandName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> creationDtm = _super.creationDtm;

    //inherited
    public final StringPath creationId = _super.creationId;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDtm = _super.modifyDtm;

    //inherited
    public final StringPath modifyId = _super.modifyId;

    public final ListPath<com.category.database.entity.product.Product, com.category.database.entity.product.QProduct> productList = this.<com.category.database.entity.product.Product, com.category.database.entity.product.QProduct>createList("productList", com.category.database.entity.product.Product.class, com.category.database.entity.product.QProduct.class, PathInits.DIRECT2);

    public final StringPath remark = createString("remark");

    public final EnumPath<com.category.common.enums.SaleStateCode> saleStateCode = createEnum("saleStateCode", com.category.common.enums.SaleStateCode.class);

    public QBrand(String variable) {
        super(Brand.class, forVariable(variable));
    }

    public QBrand(Path<? extends Brand> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBrand(PathMetadata metadata) {
        super(Brand.class, metadata);
    }

}

