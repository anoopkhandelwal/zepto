package com.example.zepto.services;

import com.example.zepto.daos.CoachDAO;
import com.example.zepto.daos.SeatDAO;
import com.example.zepto.daos.TrainDAO;
import com.example.zepto.enums.SeatStatus;
import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.Coach;
import com.example.zepto.models.Seat;
import com.example.zepto.models.Train;
import com.example.zepto.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatService {

    private SeatDAO dao = new SeatDAO();
    private CoachDAO coachDAO = new CoachDAO();
    private TrainDAO trainDAO = new TrainDAO();

    public List<Seat> getSeats(Integer trainId, Integer coachId, SeatStatus seatStatus) throws NotFoundException {

        List<Seat> seats = new ArrayList<>();

        if(trainId!=null){
            Train train = trainDAO.get(trainId);
            if(train == null){
                throw new NotFoundException(Constants.TRAIN_NOT_FOUND);
            }else{
                for(Coach coach: train.getCoaches()){
                    seats.addAll(coachDAO.getCoachSeats(coach,seatStatus));
                }
            }
        }else{
            Coach coach = coachDAO.get(coachId);
            seats.addAll(coachDAO.getCoachSeats(coach,seatStatus));
        }

        return seats;
    }
}
