package com.marine.traffic.exception;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class BusinessException extends RuntimeException {
    private String code;
    private String message;
    private Object data;

    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(ExceptionBase error) {
        this.code = error.getCode();
        this.message = error.getMessage();
    }


    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(ExceptionBase error, Object param) {
        this.code = error.getCode();
        this.message = String.format(error.getMessage(), param);
    }

    public BusinessException(ExceptionBase error, Object param, Object var) {
        this.code = error.getCode();
        this.message = String.format(error.getMessage(), param, var);
    }

    public BusinessException(Exception e) {
        super(e);
    }
}
