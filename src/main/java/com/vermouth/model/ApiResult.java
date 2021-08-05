package com.vermouth.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class ApiResult {
    private final Object data;
    private final String msg;

    public ApiResult(Map<String, Object> data) {
        this.data = data.get("data");

        this.msg = data.containsKey("msg") ? data.get("msg").toString() : "success";
    }
}