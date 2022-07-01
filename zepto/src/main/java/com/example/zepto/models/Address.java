package com.example.zepto.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
    private String firstLine;
    private Long pinCode;
}
