package com.email.builder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.email.builder.dto.ApiResponse;
import com.email.builder.dto.FieldError;
import com.email.builder.dto.ResponseMetadata;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ApiResponseBuilder {
    private static final Set<String> ALLOWED_STATUS = new HashSet(Arrays.asList("success", "error"));
    private static final String HTTP_STATUS_ERROR_MSG = "HttpStatus must not be null";
    private static final String CUSTOM_HTTP_STATUS_ERROR_MSG = "customStatusCode must not be null";

    private ApiResponseBuilder() {
    }

    public static ResponseEntity<ApiResponse> getSuccessResponse(Object data, String message, HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        ApiResponse response = buildResponse(data, "success", message, String.valueOf(httpStatus.value()), (List) null);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<ApiResponse> getErrorResponse(String errorMessage, HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        ApiResponse response = buildResponse((Object) null, "error", errorMessage, String.valueOf(httpStatus.value()), (List) null);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<ApiResponse> getErrorResponse(String errorMessage, HttpStatus httpStatus, List<FieldError> errors) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        ApiResponse response = buildResponse((Object) null, "error", errorMessage, String.valueOf(httpStatus.value()), errors);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<ApiResponse> getErrorResponse(Object data, String message, HttpStatus httpStatus, List<FieldError> errors) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        ApiResponse response = buildResponse(data, "error", message, String.valueOf(httpStatus.value()), errors);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<ApiResponse> getSuccessResponse(Object data, String message, HttpStatus httpStatus, String customStatusCode) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        Assert.notNull(customStatusCode, "customStatusCode must not be null");
        ApiResponse response = buildResponse(data, "success", message, customStatusCode, (List) null);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<ApiResponse> getErrorResponse(String errorMessage, HttpStatus httpStatus, String customStatusCode, List<FieldError> errors) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        Assert.notNull(customStatusCode, "customStatusCode must not be null");
        ApiResponse response = buildResponse((Object) null, "error", errorMessage, customStatusCode, errors);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<ApiResponse> getErrorResponse(Object data, String message, HttpStatus httpStatus, String customStatusCode, List<FieldError> errors) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        Assert.notNull(customStatusCode, "customStatusCode must not be null");
        ApiResponse response = buildResponse(data, "error", message, customStatusCode, errors);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ApiResponse buildResponse(Object data, String status, String message, String statusCode, List<FieldError> errors) {
        Assert.notNull(status, "status must not be null");
        Assert.notNull(statusCode, "statusCode must not be null");
        if (!ALLOWED_STATUS.contains(status)) {
            throw new IllegalArgumentException("Illegal status [" + status + "]. ALLOWED_STATUS " + Arrays.toString(ALLOWED_STATUS.toArray()));
        } else {
            ResponseMetadata meta = getResponseMetadata(status, (Page) null);
            return ApiResponse.builder().status(status).message(message != null ? message : status).statusCode(statusCode).data(data).meta(meta).errors(errors).build();
        }
    }

    public static <U> ResponseEntity<ApiResponse> getPaginationSuccessResponse(Page<U> page, String message, HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        ApiResponse response = buildPaginationResponse(page, "success", message, String.valueOf(httpStatus.value()), (List) null);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <U> ResponseEntity<ApiResponse> getPaginationErrorResponse(Page<U> page, String message, HttpStatus httpStatus, List<FieldError> errors) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        ApiResponse response = buildPaginationResponse(page, "error", message, String.valueOf(httpStatus.value()), errors);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <U> ResponseEntity<ApiResponse> getPaginationSuccessResponse(Page<U> page, String message, HttpStatus httpStatus, String customStatusCode) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        Assert.notNull(customStatusCode, "customStatusCode must not be null");
        ApiResponse response = buildPaginationResponse(page, "success", message, customStatusCode, (List) null);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <U> ResponseEntity<ApiResponse> getPaginationErrorResponse(Page<U> page, String message, HttpStatus httpStatus, String customStatusCode, List<FieldError> errors) {
        Assert.notNull(httpStatus, "HttpStatus must not be null");
        Assert.notNull(customStatusCode, "customStatusCode must not be null");
        ApiResponse response = buildPaginationResponse(page, "error", message, customStatusCode, errors);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <U> ApiResponse buildPaginationResponse(Page<U> page, String status, String message, String statusCode, List<FieldError> errors) {
        Assert.notNull(status, "status must not be null");
        Assert.notNull(statusCode, "statusCode must not be null");
        if (!ALLOWED_STATUS.contains(status)) {
            throw new IllegalArgumentException("Illegal status [" + status + "]. ALLOWED_STATUS " + Arrays.toString(ALLOWED_STATUS.toArray()));
        } else {
            ResponseMetadata meta = getResponseMetadata(status, page);
            return ApiResponse.builder().status(status).message(message != null ? message : status).statusCode(statusCode).data(page != null ? page.getContent() : null).meta(meta).errors(errors).build();
        }
    }

    private static <U> ResponseMetadata getResponseMetadata(String status, Page<U> page) {
        ResponseMetadata meta;
        if (ObjectUtils.isNotEmpty(page)) {
            meta = new ResponseMetadata(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages(), page.isFirst(), page.isLast());
        } else {
            meta = new ResponseMetadata();
        }

        meta.setTimestamp(System.currentTimeMillis());
        if ("error".equalsIgnoreCase(status)) {
        }

        return meta;
    }
}
