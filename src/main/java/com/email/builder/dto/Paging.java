package com.email.builder.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Paging implements Serializable {
    private static final long serialVersionUID = 5830511735456698918L;
    private int pageNumber;
    private int pageSize;
    private List<Sort> sort;

    @JsonIgnore
    public org.springframework.data.domain.Sort getSortInstance() {
        if (this.sort != null && !this.sort.isEmpty()) {
            List<org.springframework.data.domain.Sort.Order> orders = new ArrayList();
            Iterator var2 = this.sort.iterator();

            while(var2.hasNext()) {
                Sort s = (Sort)var2.next();
                if (StringUtils.isNotBlank(s.getProperty())) {
                    if ("desc".equalsIgnoreCase(s.getDirection())) {
                        orders.add(Order.desc(s.getProperty()));
                    } else {
                        orders.add(Order.asc(s.getProperty()));
                    }
                }
            }

            if (!orders.isEmpty()) {
                return org.springframework.data.domain.Sort.by(orders);
            }
        }

        return null;
    }

    @JsonIgnore
    public Pageable getPageableInstance() {
        Pageable pageable = null;
        if (this.pageNumber >= 0 && this.pageSize > 0) {
            org.springframework.data.domain.Sort s = this.getSortInstance();
            if (s != null) {
                pageable = PageRequest.of(this.pageNumber, this.pageSize, s);
            } else {
                pageable = PageRequest.of(this.pageNumber, this.pageSize);
            }
        }

        return pageable;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return this.pageNumber < 0 || this.pageSize <= 0;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public List<Sort> getSort() {
        return this.sort;
    }

    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(final List<Sort> sort) {
        this.sort = sort;
    }

    public Paging(final int pageNumber, final int pageSize, final List<Sort> sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public Paging() {
    }
}
