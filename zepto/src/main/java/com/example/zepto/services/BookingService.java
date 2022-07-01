package com.example.zepto.services;

import com.example.zepto.daos.*;
import com.example.zepto.enums.SeatStatus;
import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.*;
import com.example.zepto.pojos.SeatBookingRequest;
import com.example.zepto.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingService {

    private SeatDAO seatDAO = new SeatDAO();
    private CoachDAO coachDAO = new CoachDAO();
    private TrainDAO trainDAO = new TrainDAO();
    private UserDAO userDAO = new UserDAO();
    private BookingDAO dao = new BookingDAO();

    public Booking bookedSeats(Integer userId, SeatBookingRequest request) throws NotFoundException {

        List<Seat> seats = new ArrayList<>();

        Train train = trainDAO.get(request.getTrainId());
        Coach coach = coachDAO.get(request.getCoachId());
        User user = userDAO.get(userId);
        if(train == null){
            throw new NotFoundException(Constants.TRAIN_NOT_FOUND);
        }
        if(coach == null){
            throw new NotFoundException(Constants.COACH_NOT_FOUND);
        }
        if(user == null){
            throw new NotFoundException(Constants.USER_NOT_FOUND);
        }
        else{

            for(Integer seatId : request.getSeats()){
                Seat seat = coachDAO.getCoachSeat(coach,seatId);
                if(seat==null){
                    throw new NotFoundException(Constants.SEAT_NOT_FOUND);
                }
                if(SeatStatus.BOOKED.equals(seat.getStatus())){
                    throw new NotFoundException(Constants.SEAT_ALREADY_BOOKED);
                }else {
                    seats.add(seat);
                }
            }
        }

        return dao.create(seats,request.getBookingDate(),user);

    }

    public List<Booking> getBookings(){
        return dao.getBookings();
    }

    public Integer cancelBooking(Integer bookingId){
        return dao.delete(bookingId);
    }
}
