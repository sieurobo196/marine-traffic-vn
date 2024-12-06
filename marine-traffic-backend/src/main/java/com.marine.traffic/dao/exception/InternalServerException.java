package com.marine.traffic.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum InternalServerException implements ExceptionBase {
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "There is an internal server error. Please contact to Admin"),
    INTERNAL_DATABASE_ERROR("INTERNAL_DATABASE_ERROR", "There is an internal server error. Please contact to Admin"),
    ;
    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
