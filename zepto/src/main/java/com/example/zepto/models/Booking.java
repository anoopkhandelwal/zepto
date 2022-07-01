package com.example.zepto.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Booking {
    private UUID uuid;
    private Integer id;
    private List<Seat> seats;
    private User user;
    private BookingDate bookingDate;
}
