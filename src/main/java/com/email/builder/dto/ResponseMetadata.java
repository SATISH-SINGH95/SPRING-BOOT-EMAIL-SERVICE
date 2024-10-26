package com.email.builder.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseMetadata implements Serializable {
    private static final long serialVersionUID = 5616720503120832647L;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean firstPage;
    private Boolean lastPage;
    private long timestamp;
    private String correlationId;

    public ResponseMetadata(Integer pageNumber, Integer pageSize, Long totalElements, Integer totalPages, Boolean firstPage, Boolean lastPage) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Long getTotalElements() {
        return this.totalElements;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public Boolean getFirstPage() {
        return this.firstPage;
    }

    public Boolean getLastPage() {
        return this.lastPage;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getCorrelationId() {
        return this.correlationId;
    }

    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalElements(final Long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(final Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void setFirstPage(final Boolean firstPage) {
        this.firstPage = firstPage;
    }

    public void setLastPage(final Boolean lastPage) {
        this.lastPage = lastPage;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    public void setCorrelationId(final String correlationId) {
        this.correlationId = correlationId;
    }

    public ResponseMetadata(final Integer pageNumber, final Integer pageSize, final Long totalElements, final Integer totalPages, final Boolean firstPage, final Boolean lastPage, final long timestamp, final String correlationId) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.timestamp = timestamp;
        this.correlationId = correlationId;
    }

    public ResponseMetadata() {
    }
}
