package com.marine.traffic.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum HttpUtilsException implements ExceptionBase {
        REQ_METHOD_ERROR("REQUEST_METHOD_ERROR", "The request method is empty"),
        REQ_TOKEN_ERROR("REQUEST_TOKEN_ERROR", "The request token is empty"),
        REQ_URL_ERROR("REQUEST_URL_ERROR", "The request Url is empty"),
        REQ_SENDING_TO_CF_ERROR("REQUEST_SENDING_TO_CF_ERROR", "There is an issue when sending request to 3rd Cloudflare"),
        REQ_PARSING_TO_JSON_OBJ_ERROR("REQUEST_PARSING_TO_JSON_OBJ_ERROR", "There is an issue when parsing Request Object to Json String"),

        RES_OBJ_MAPPING_ERROR("RESPONSE_OBJ_MAPPING_ERROR", "The reponse object mapper is empty"),
        RES_FROM_CF_ERROR("RESPONSE_FROM_CF_ERROR", "The response after calling to Cloudflare is empty"),
        RES_PARSING_TO_JSON_OBJ_ERROR("RESPONSE_PARSING_TO_JSON_OBJ_ERROR", "There is an issue on response when parsing json String to Object"),
        RES_PARSING_TO_JSON_OBJ_UNKNOWN_PROP_ERROR("RESPONSE_PARSING_TO_JSON_OBJ_UNKNOWN_PROP_ERROR", "The parsing json to object is unrecognized property");

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
