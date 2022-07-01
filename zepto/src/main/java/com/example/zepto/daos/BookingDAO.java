package com.example.zepto.daos;

import com.example.zepto.enums.SeatStatus;
import com.example.zepto.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingDAO {
    private static List<Booking> bookings = new ArrayList<>();
    private Integer BOOKINGS_COUNTER = 1;

    public Booking get(UUID uuid){
        for(Booking booking : bookings){
            if(booking.getUuid().equals(uuid)){
                return booking;
            }
        }
        return null;
    }

    public Booking create(List<Seat> seats, BookingDate date, User user){

        seats.forEach(seat -> seat.setStatus(SeatStatus.BOOKED));

        Booking booking = Booking.builder()
                .uuid(UUID.randomUUID())
                .id(BOOKINGS_COUNTER++)
                .bookingDate(date)
                .seats(seats)
                .user(user)
                .build();
        bookings.add(booking);
        return booking;
    }

    public int delete(UUID uuid){
        for(Booking booking : bookings){
            if(booking.getUuid().equals(uuid)){
                for (Seat seat : booking.getSeats()) {
                    seat.setStatus(SeatStatus.AVAILABLE);
                }
                bookings.remove(booking);
                return 1;
            }
        }
        return -1;
    }

    public int delete(Integer id){
        for(Booking booking : bookings){
            if(booking.getId().equals(id)){
                for (Seat seat : booking.getSeats()) {
                    seat.setStatus(SeatStatus.AVAILABLE);
                }
                bookings.remove(booking);
                return 1;
            }
        }
        return -1;
    }

    public List<Booking> getBookings(){
        return bookings;
    }


}
