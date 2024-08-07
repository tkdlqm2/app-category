package com.category.brand.controller.insertController;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/insert")
public class InsertEntity {
    private static final String JDBC_URL = "jdbc:h2:mem:musinsa;NON_KEYWORDS=brand";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @GetMapping("/brand")
    public void insertBrand() {
        String sql = "INSERT INTO brand (brand_Key, brand_name, sale_state_code, remark) VALUES (?, ?, ?, ?)";

        // 삽입할 데이터
        String[][] brandData = {
                {"B0001", "A", "SALE", null},
                {"B0002", "B", "SALE", null},
                {"B0003", "C", "SALE", null},
                {"B0004", "D", "SALE", null},
                {"B0005", "E", "SALE", null},
                {"B0006", "F", "SALE", null},
                {"B0007", "G", "SALE", null},
                {"B0008", "H", "SALE", null},
                {"B0009", "I", "SALE", null}
        };

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);  // 트랜잭션 시작

            for (String[] data : brandData) {
                pstmt.setString(1, data[0]);  // brand_Key
                pstmt.setString(2, data[1]);  // brand_name
                pstmt.setString(3, data[2]);  // sale_state_code
                pstmt.setString(4, data[3]);  // remark

                pstmt.addBatch();  // 배치에 추가
            }

            int[] result = pstmt.executeBatch();  // 배치 실행
            conn.commit();  // 트랜잭션 커밋

            System.out.println("데이터 삽입 완료. 삽입된 행 수: " + result.length);

        } catch (SQLException e) {
            System.err.println("데이터 삽입 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @GetMapping("/product")
    public void insertProduct() {
        String sql = "INSERT INTO product (price, brand_id, product_key, category_type, remark) VALUES (?, ?, ?, ?, ?)";

        // 삽입할 데이터
        Object[][] productData = {
                // Brand A products
                {11200, 1, "A_TOP", "TOP", null},
                {5500, 1, "A_OUTERWEAR", "OUTERWEAR", null},
                {4200, 1, "A_PANTS", "PANTS", null},
                {9000, 1, "A_SNEAKERS", "SNEAKERS", null},
                {2000, 1, "A_BAG", "BAG", null},
                {1700, 1, "A_HAT", "HAT", null},
                {1800, 1, "A_SOCKS", "SOCKS", null},
                {2300, 1, "A_ACCESSORY", "ACCESSORY", null},

                // Brand B products
                {10500, 2, "B_TOP", "TOP", null},
                {5900, 2, "B_OUTERWEAR", "OUTERWEAR", null},
                {3800, 2, "B_PANTS", "PANTS", null},
                {9100, 2, "B_SNEAKERS", "SNEAKERS", null},
                {2100, 2, "B_BAG", "BAG", null},
                {2000, 2, "B_HAT", "HAT", null},
                {2000, 2, "B_SOCKS", "SOCKS", null},
                {2200, 2, "B_ACCESSORY", "ACCESSORY", null},

                // Brand C products
                {10000, 3, "C_TOP", "TOP", null},
                {6200, 3, "C_OUTERWEAR", "OUTERWEAR", null},
                {3300, 3, "C_PANTS", "PANTS", null},
                {9200, 3, "C_SNEAKERS", "SNEAKERS", null},
                {2200, 3, "C_BAG", "BAG", null},
                {1900, 3, "C_HAT", "HAT", null},
                {2200, 3, "C_SOCKS", "SOCKS", null},
                {2100, 3, "C_ACCESSORY", "ACCESSORY", null},

                // Brand D products
                {10100, 4, "D_TOP", "TOP", null},
                {5100, 4, "D_OUTERWEAR", "OUTERWEAR", null},
                {3000, 4, "D_PANTS", "PANTS", null},
                {9500, 4, "D_SNEAKERS", "SNEAKERS", null},
                {2500, 4, "D_BAG", "BAG", null},
                {1500, 4, "D_HAT", "HAT", null},
                {2400, 4, "D_SOCKS", "SOCKS", null},
                {2000, 4, "D_ACCESSORY", "ACCESSORY", null},

                // Brand E products
                {10700, 5, "E_TOP", "TOP", null},
                {5000, 5, "E_OUTERWEAR", "OUTERWEAR", null},
                {3800, 5, "E_PANTS", "PANTS", null},
                {9900, 5, "E_SNEAKERS", "SNEAKERS", null},
                {2300, 5, "E_BAG", "BAG", null},
                {1800, 5, "E_HAT", "HAT", null},
                {2100, 5, "E_SOCKS", "SOCKS", null},
                {2100, 5, "E_ACCESSORY", "ACCESSORY", null},

                // Brand F products
                {11200, 6, "F_TOP", "TOP", null},
                {7200, 6, "F_OUTERWEAR", "OUTERWEAR", null},
                {4000, 6, "F_PANTS", "PANTS", null},
                {9300, 6, "F_SNEAKERS", "SNEAKERS", null},
                {2100, 6, "F_BAG", "BAG", null},
                {1600, 6, "F_HAT", "HAT", null},
                {2300, 6, "F_SOCKS", "SOCKS", null},
                {1900, 6, "F_ACCESSORY", "ACCESSORY", null},

                // Brand G products
                {10500, 7, "G_TOP", "TOP", null},
                {5800, 7, "G_OUTERWEAR", "OUTERWEAR", null},
                {3900, 7, "G_PANTS", "PANTS", null},
                {9000, 7, "G_SNEAKERS", "SNEAKERS", null},
                {2200, 7, "G_BAG", "BAG", null},
                {1700, 7, "G_HAT", "HAT", null},
                {2100, 7, "G_SOCKS", "SOCKS", null},
                {2000, 7, "G_ACCESSORY", "ACCESSORY", null},

                // Brand H products
                {10800, 8, "H_TOP", "TOP", null},
                {6300, 8, "H_OUTERWEAR", "OUTERWEAR", null},
                {3100, 8, "H_PANTS", "PANTS", null},
                {9700, 8, "H_SNEAKERS", "SNEAKERS", null},
                {2100, 8, "H_BAG", "BAG", null},
                {1600, 8, "H_HAT", "HAT", null},
                {2000, 8, "H_SOCKS", "SOCKS", null},
                {2000, 8, "H_ACCESSORY", "ACCESSORY", null},

                // Brand I products
                {11400, 9, "I_TOP", "TOP", null},
                {6700, 9, "I_OUTERWEAR", "OUTERWEAR", null},
                {3200, 9, "I_PANTS", "PANTS", null},
                {9500, 9, "I_SNEAKERS", "SNEAKERS", null},
                {2400, 9, "I_BAG", "BAG", null},
                {1700, 9, "I_HAT", "HAT", null},
                {1700, 9, "I_SOCKS", "SOCKS", null},
                {2400, 9, "I_ACCESSORY", "ACCESSORY", null}
        };

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);  // 트랜잭션 시작

            for (Object[] data : productData) {
                pstmt.setInt(1, (Integer) data[0]);     // price
                pstmt.setInt(2, (Integer) data[1]);     // brand_id
                pstmt.setString(3, (String) data[2]);   // product_key
                pstmt.setString(4, (String) data[3]);   // category_type
                pstmt.setString(5, (String) data[4]);   // remark

                pstmt.addBatch();  // 배치에 추가
            }

            int[] result = pstmt.executeBatch();  // 배치 실행
            conn.commit();  // 트랜잭션 커밋

            System.out.println("데이터 삽입 완료. 삽입된 행 수: " + result.length);

        } catch (SQLException e) {
            System.err.println("데이터 삽입 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
