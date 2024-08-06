package com.category.database.repository;

import com.category.database.entity.product.Product;
import com.category.database.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    boolean existsByBrandIdAndProductKey(Long brandId, String productKey);
    void deleteById(Long id);

}
