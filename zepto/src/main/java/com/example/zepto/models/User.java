package com.example.zepto.models;

import com.example.zepto.enums.Gender;
import com.example.zepto.enums.UserType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class User {

    private UUID uuid;
    private Integer id;
    private String name;
    private Integer age;
    private Gender gender;
    private String address;
    private Long pinCode;
    private UserType userType;
}
