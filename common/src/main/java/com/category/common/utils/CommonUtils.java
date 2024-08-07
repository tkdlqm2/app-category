package com.category.common.utils;

import com.category.common.dto.ResponseDTO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    public static <T> ResponseDTO<T> createSuccessResponse(int code, String message, T response) {
        return ResponseDTO.<T>builder()
                .code(code)
                .message(message)
                .response(response)
                .build();
    }

    public static ResponseDTO createFailureResponse(int code, String message, Object response) {
        return ResponseDTO.builder()
                .code(code)
                .message(message)
                .response(response)
                .build();
    }

    public static <T> List<String> getNullFields(T object) {
        List<String> nullFields = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(object) == null) {
                    nullFields.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return nullFields;
    }
}
