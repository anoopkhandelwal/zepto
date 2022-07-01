package com.example.zepto;

import com.example.zepto.enums.CoachType;
import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.Train;
import com.example.zepto.services.TrainService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TrainTests {

    @InjectMocks
    private TrainService trainService;

    Train train = Train.builder()
            .from("Alwar")
            .to("Kashmir")
            .build();

    @Test
    public void createTrainTest() throws NotFoundException {
        Train newTrain = trainService.createTrain("Alwar", "Kashmir");
        Assert.assertNotNull(newTrain);
        Assert.assertEquals(newTrain.getFrom(),train.getFrom());
        Assert.assertEquals(newTrain.getTo(),train.getTo());
        trainService.deleteTrain(newTrain.getId());
    }

    @Test
    public void addCoachTest() throws NotFoundException {
        Train train = trainService.createTrain("Alwar", "Kashmir");
        train = trainService.addCoach(train.getId(), CoachType.NON_AC);
        Assert.assertNotNull(train);
        Assert.assertEquals(train.getCoaches().get(0).getId().toString(),String.valueOf(train.getCoaches().size()));
        Assert.assertEquals(train.getFrom(),train.getFrom());
        Assert.assertEquals(train.getTo(),train.getTo());
        int status = trainService.removeCoach(train.getId(), train.getCoaches().get(0).getId());
        Assert.assertEquals(status,1);
    }
}
