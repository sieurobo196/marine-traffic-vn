package com.marine.traffic.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum BaseIntegrationException implements ExceptionBase {
   REQ_JSON_DEPLOYMENT_EMPTY("REQUEST_JSON_DEPLOYMENT_EMPTY", "Deployment Json Data request is empty"),
   REQ_ACTION_EMPTY("REQUEST_ACTION_EMPTY", "Can't detect action to process");

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
