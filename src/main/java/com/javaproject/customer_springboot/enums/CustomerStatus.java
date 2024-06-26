package com.javaproject.customer_springboot.enums;

public enum CustomerStatus {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String customerStatus;

    private CustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }
}