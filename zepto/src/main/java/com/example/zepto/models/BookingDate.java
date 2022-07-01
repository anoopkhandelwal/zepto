package com.example.zepto.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class BookingDate {
    private Date departure;
    private Date arrival;

}
