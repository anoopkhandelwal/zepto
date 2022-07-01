package com.example.zepto.pojos;

import com.example.zepto.enums.Gender;
import com.example.zepto.enums.UserType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddUserRequest {

    private String name;
    private UserType userType;
    private Integer age;
    private Gender gender;
    private String address;
    private Long pinCode;

}
