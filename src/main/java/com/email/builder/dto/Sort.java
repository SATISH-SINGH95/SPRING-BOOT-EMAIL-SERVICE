package com.email.builder.dto;

import java.io.Serializable;

public class Sort implements Serializable {
    private static final long serialVersionUID = 6660702712842552093L;
    public static final String DIRECTION_ASC = "asc";
    public static final String DIRECTION_DESC = "desc";
    private String property;
    private String direction;

    public String getProperty() {
        return this.property;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setProperty(final String property) {
        this.property = property;
    }

    public void setDirection(final String direction) {
        this.direction = direction;
    }

    public Sort(final String property, final String direction) {
        this.property = property;
        this.direction = direction;
    }

    public Sort() {
    }
}