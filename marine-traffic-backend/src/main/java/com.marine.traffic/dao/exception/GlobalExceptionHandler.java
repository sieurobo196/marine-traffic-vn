package com.marine.traffic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String BUSINESS = "BUSINESS";
    private static final String UNEXPECTED = "UNEXPECTED";

    /**
     * Handle all businesses exception
     *
     * @param
     * @return
     */

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date().toString());
        body.put("errorMsg", ex.getMessage());
        body.put("errorCode", ex.getCode());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle for unexpected exception
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedError(Exception ex) {
        BusinessException exception = new BusinessException(InternalServerException.INTERNAL_SERVER_ERROR);
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date().toString());
        body.put("errorMsg", ex.getMessage());
        body.put("errorCode", exception.getCode());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
