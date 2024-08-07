package com.category.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum CategoryType {
    TOP("상의"),
    OUTERWEAR("아우터"),
    PANTS("바지"),
    SNEAKERS("스니커즈"),
    BAG("가방"),
    HAT("모자"),
    SOCKS("양말"),
    ACCESSORY("악세사리")
    ;
    private final String description;

    private static final Map<String, CategoryType> descriptionToEnum = new HashMap<>();

    static {
        for (CategoryType categoryType : values()) {
            descriptionToEnum.put(categoryType.getDescription(), categoryType);
        }
    }

    public static CategoryType fromDescription(String description) {
        CategoryType categoryType = descriptionToEnum.get(description);
        if (categoryType == null) {
            throw new IllegalArgumentException("다루지 않는 카테고리 입니다. : " + description);
        }
        return categoryType;
    }
}
