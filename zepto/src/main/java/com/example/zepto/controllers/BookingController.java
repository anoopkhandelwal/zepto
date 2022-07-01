package com.example.zepto.controllers;
import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.Booking;
import com.example.zepto.pojos.SeatBookingRequest;
import com.example.zepto.services.BookingService;
import com.example.zepto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Booking createBooking(@RequestBody SeatBookingRequest request,
                                 @RequestParam(value = "userId") Integer userId ) throws NotFoundException {

        return bookingService.bookedSeats(userId,request);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Booking> getSeats(@RequestParam(value = "userId") Integer userId,
                                  @RequestParam(value = "limit",defaultValue = "100") Integer limit,
                                  @RequestParam(value = "offset" , defaultValue = "1") Integer offset) throws NotFoundException {

        userService.isAdmin(userId);
        return bookingService.getBookings();
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Integer cancelBooking(@RequestParam(value = "userId") Integer userId,
                                  @RequestParam(value = "bookingId") Integer bookingId
                                  ) throws NotFoundException {

        userService.isAdmin(userId);
        return bookingService.cancelBooking(bookingId);
    }


}
