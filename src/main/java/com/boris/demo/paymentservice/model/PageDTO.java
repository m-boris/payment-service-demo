package com.boris.demo.paymentservice.model;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {
    private List<T> content;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
}
