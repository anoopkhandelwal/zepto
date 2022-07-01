package com.example.zepto.controllers;

import com.example.zepto.enums.SeatStatus;
import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.Seat;
import com.example.zepto.services.SeatService;
import com.example.zepto.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/seat")
@Slf4j
public class SeatController {

    @Autowired
    private SeatService seatService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Seat> getSeats(@RequestParam(value = "trainId") Integer trainId,
                               @RequestParam(value = "coachId") Integer coachId,
                               @RequestParam(value = "userId") Integer userId,
                               @RequestParam(value = "status") SeatStatus seatStatus,
                               @RequestParam(value = "limit",defaultValue = "100") Integer limit,
                               @RequestParam(value = "offset" , defaultValue = "1") Integer offset) throws NotFoundException {

        userService.isAdmin(userId);
        return seatService.getSeats(trainId,coachId, seatStatus);
    }

}
