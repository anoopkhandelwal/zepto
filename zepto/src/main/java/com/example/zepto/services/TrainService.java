package com.example.zepto.services;

import com.example.zepto.daos.CoachDAO;
import com.example.zepto.daos.TrainDAO;
import com.example.zepto.enums.CoachType;
import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.Coach;
import com.example.zepto.models.Train;
import com.example.zepto.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainService {

    private TrainDAO dao = new TrainDAO();
    private CoachDAO coachDAO = new CoachDAO();

    public List<Train> getTrains(){
        return dao.getAll();
    }

    public Train createTrain(String from ,String to){

        return dao.create(from,to);
    }

    public Train addCoach(Integer trainId ,CoachType coachType) throws NotFoundException {

        Train train = dao.get(trainId);
        if(train == null){
            throw new NotFoundException(Constants.TRAIN_NOT_FOUND);
        }

        Coach coach = coachDAO.create(coachType,trainId);

        train.getCoaches().add(coach);
        return train;

    }

    public int removeCoach(Integer trainId ,Integer coachId) throws NotFoundException {

        Train train = dao.get(trainId);
        if(train == null){
            throw new NotFoundException(Constants.TRAIN_NOT_FOUND);
        }
        Coach coach = coachDAO.get(coachId);
        if(coach == null){
            throw new NotFoundException(Constants.COACH_NOT_FOUND);
        }
        train.getCoaches().remove(coach);
        int status = coachDAO.delete(coachId);
        return status;

    }

    public int deleteTrain(Integer trainId) throws NotFoundException {
        Train train = dao.get(trainId);
        if(train == null){
            throw new NotFoundException(Constants.TRAIN_NOT_FOUND);
        }
        for(Coach coach: train.getCoaches()){
            int status = coachDAO.delete(coach.getId());
            if(status!=1){
                return status;
            }
        }
        return dao.delete(trainId);
    }
}
