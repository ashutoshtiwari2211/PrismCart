package com.prismcart.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A standardized wrapper for all API responses.
 * @param <T> The type of the data payload.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    // Static helper methods for creating common responses

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data);
    }

    public static ApiResponse<String> success(String message) {
        return new ApiResponse<>(true, message, null);
    }

    public static ApiResponse<String> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
