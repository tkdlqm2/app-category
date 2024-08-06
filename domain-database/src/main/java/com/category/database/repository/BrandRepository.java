package com.category.database.repository;

import com.category.database.entity.brand.Brand;
import com.category.database.repository.custom.BrandRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, BrandRepositoryCustom {
    boolean existsByBrandkeyAndBrandName(String brandkey, String brandName);
    Optional<Brand> findById(Long brandId);
    List<Brand> findAll();
    void deleteBrandById(Long id);
}