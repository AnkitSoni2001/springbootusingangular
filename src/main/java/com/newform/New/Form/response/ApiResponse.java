package com.newform.New.Form.response;

import java.util.List;

public class ApiResponse<T> {
    private String message;
    private List<T> data;

    public ApiResponse(String message, List<T> data) {
        this.message = message;
        this.data = data;
    }
    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }
}
