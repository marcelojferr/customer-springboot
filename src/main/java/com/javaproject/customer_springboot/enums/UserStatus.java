package com.javaproject.customer_springboot.enums;

public enum UserStatus {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String userStatus;

    private UserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}