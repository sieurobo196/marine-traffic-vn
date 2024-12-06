package com.marine.traffic.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDto error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
