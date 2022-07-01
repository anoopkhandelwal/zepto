package com.example.zepto.pojos;

import com.example.zepto.models.BookingDate;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SeatBookingRequest {

    private BookingDate bookingDate;
    private Integer trainId;
    private Integer coachId;
    private List<Integer> seats = new ArrayList<>();

}
