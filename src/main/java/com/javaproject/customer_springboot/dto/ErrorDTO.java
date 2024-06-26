package com.javaproject.customer_springboot.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ErrorDTO {

    protected String title;
    protected Integer status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;
}
