package com.example.zepto.controllers;

import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.Train;
import com.example.zepto.pojos.AddCoachRequest;
import com.example.zepto.pojos.CreateTrainRequest;
import com.example.zepto.pojos.RemoveCoachRequest;
import com.example.zepto.services.TrainService;
import com.example.zepto.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/train")
@Slf4j
public class TrainController {
    @Autowired
    private TrainService trainService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Train> getAllTrains(@RequestParam(value = "userId") Integer userId,
                                    @RequestParam(value = "limit",defaultValue = "100") Integer limit,
                                    @RequestParam(value = "offset" , defaultValue = "1") Integer offset) throws NotFoundException {
        userService.isAdmin(userId);
        return trainService.getTrains();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Train createTrain(@RequestBody CreateTrainRequest request,
                             @RequestParam(value = "userId") Integer userId) throws NotFoundException {
        userService.isAdmin(userId);
        return trainService.createTrain(request.getFrom(),request.getTo());
    }

    @RequestMapping(value = "/coach", method = RequestMethod.PUT)
    public Train addCoach(@RequestBody AddCoachRequest request,
                          @RequestParam(value = "userId") Integer userId) throws NotFoundException {
        userService.isAdmin(userId);
        return trainService.addCoach(request.getTrainId(),request.getCoachType());
    }

    @RequestMapping(value = "/coach", method = RequestMethod.DELETE)
    public int removeCoach(@RequestBody RemoveCoachRequest request,
                           @RequestParam(value = "userId") Integer userId) throws NotFoundException {
        userService.isAdmin(userId);
        return trainService.removeCoach(request.getTrainId(),request.getCoachId());
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public Integer deleteTrain(@RequestParam(value = "trainId") Integer trainId,
                             @RequestParam(value = "userId") Integer userId) throws NotFoundException {
        userService.isAdmin(userId);
        return trainService.deleteTrain(trainId);
    }

}
