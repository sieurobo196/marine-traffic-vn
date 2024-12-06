package com.marine.traffic.model;

import com.marine.traffic.exception.ExceptionBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String code;
    private String message;

    public ErrorDto(ExceptionBase exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

}
