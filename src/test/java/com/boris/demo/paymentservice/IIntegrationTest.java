package com.boris.demo.paymentservice;

public interface IIntegrationTest {

    final String BASE_URL="http://localhost:8081";

    default String getFullUrl(String url){
        return BASE_URL+url;
    }
}
