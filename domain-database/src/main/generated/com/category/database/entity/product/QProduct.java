package com.category.database.entity.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 608328053L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.category.database.entity.QBaseEntity _super = new com.category.database.entity.QBaseEntity(this);

    public final com.category.database.entity.brand.QBrand brand;

    public final EnumPath<com.category.common.enums.CategoryType> categoryType = createEnum("categoryType", com.category.common.enums.CategoryType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> creationDtm = _super.creationDtm;

    //inherited
    public final StringPath creationId = _super.creationId;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDtm = _super.modifyDtm;

    //inherited
    public final StringPath modifyId = _super.modifyId;

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final StringPath productKey = createString("productKey");

    public final StringPath remark = createString("remark");

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brand = inits.isInitialized("brand") ? new com.category.database.entity.brand.QBrand(forProperty("brand")) : null;
    }

}

