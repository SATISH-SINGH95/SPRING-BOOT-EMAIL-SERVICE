package com.email.builder.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 2203986815774042259L;
    private String status;
    private String message;
    private String statusCode;
    private transient Object data;
    private ResponseMetadata meta;
    private List<FieldError> errors;

    public static ApiResponseBuilder builder() {
        return new ApiResponseBuilder();
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public Object getData() {
        return this.data;
    }

    public ResponseMetadata getMeta() {
        return this.meta;
    }

    public List<FieldError> getErrors() {
        return this.errors;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public void setMeta(final ResponseMetadata meta) {
        this.meta = meta;
    }

    public void setErrors(final List<FieldError> errors) {
        this.errors = errors;
    }

    public ApiResponse(final String status, final String message, final String statusCode, final Object data, final ResponseMetadata meta, final List<FieldError> errors) {
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
        this.meta = meta;
        this.errors = errors;
    }

    public ApiResponse() {
    }

    public String toString() {
        String var10000 = this.getStatus();
        return "ApiResponse(status=" + var10000 + ", message=" + this.getMessage() + ", statusCode=" + this.getStatusCode() + ", data=" + this.getData() + ", meta=" + this.getMeta() + ", errors=" + this.getErrors() + ")";
    }

    public static class ApiResponseBuilder {
        private String status;
        private String message;
        private String statusCode;
        private Object data;
        private ResponseMetadata meta;
        private List<FieldError> errors;

        ApiResponseBuilder() {
        }

        public ApiResponseBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public ApiResponseBuilder message(final String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder statusCode(final String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ApiResponseBuilder data(final Object data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder meta(final ResponseMetadata meta) {
            this.meta = meta;
            return this;
        }

        public ApiResponseBuilder errors(final List<FieldError> errors) {
            this.errors = errors;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this.status, this.message, this.statusCode, this.data, this.meta, this.errors);
        }

        public String toString() {
            return "ApiResponse.ApiResponseBuilder(status=" + this.status + ", message=" + this.message + ", statusCode=" + this.statusCode + ", data=" + this.data + ", meta=" + this.meta + ", errors=" + this.errors + ")";
        }
    }
}
