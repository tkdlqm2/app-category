package com.category.category.common.util;

import com.category.common.enums.CategoryType;
import com.category.common.enums.SaleStateCode;
import com.category.database.entity.brand.Brand;
import com.category.database.entity.product.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {
    private static final String FILE_NAME = "product.txt";

    public static List<Brand> generateBrand() {
        List<Brand> brands = new ArrayList<>();

        Brand brand1 = Brand.builder()
                .brandkey("B0001")
                .brandName("A")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand1);

        Brand brand2 = Brand.builder()
                .brandkey("B0002")
                .brandName("B")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand2);

        Brand brand3 = Brand.builder()
                .brandkey("B0003")
                .brandName("C")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand3);

        Brand brand4 = Brand.builder()
                .brandkey("B0004")
                .brandName("D")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand4);

        Brand brand5 = Brand.builder()
                .brandkey("B0005")
                .brandName("E")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand5);

        Brand brand6 = Brand.builder()
                .brandkey("B0006")
                .brandName("F")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand6);

        Brand brand7 = Brand.builder()
                .brandkey("B0007")
                .brandName("G")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand7);

        Brand brand8 = Brand.builder()
                .brandkey("B0008")
                .brandName("H")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand8);

        Brand brand9 = Brand.builder()
                .brandkey("B0009")
                .brandName("I")
                .saleStateCode(SaleStateCode.SALE)
                .build();
        brands.add(brand9);
        return brands;
    }

    public static List<Product> generateProducts(List<Brand> brandlist) {
        List<Product> products = new ArrayList<>();
        String filePath = Paths.get(".", FILE_NAME).toAbsolutePath().normalize().toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Brand brand = brandlist.get(Integer.parseInt(parts[1]));
                if (parts.length >= 4) {
                    Product product = Product.builder()
                            .price(new BigDecimal(parts[0]))
                            .brand(brand)
                            .productKey(parts[2])
                            .categoryType(CategoryType.valueOf(parts[3]))
                            .remark(parts.length > 4 && !parts[4].equals("NULL") ? parts[4] : null)
                            .build();
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
