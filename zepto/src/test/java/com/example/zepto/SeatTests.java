package com.example.zepto;

import com.example.zepto.enums.CoachType;
import com.example.zepto.enums.SeatStatus;
import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.Seat;
import com.example.zepto.models.Train;
import com.example.zepto.services.SeatService;
import com.example.zepto.services.TrainService;
import com.example.zepto.utils.BookingUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class SeatTests {

    @InjectMocks
    private SeatService seatService;

    @InjectMocks
    private TrainService trainService;


    @Test
    public void getSeatTest() throws NotFoundException {

        Train train = trainService.createTrain("Alwar", "Kashmir");
        trainService.addCoach(train.getId(),CoachType.AC);
        List<Seat> aSeatsList = seatService.getSeats(train.getId(), train.getCoaches().get(0).getId(), SeatStatus.AVAILABLE);
        Assert.assertNotNull(aSeatsList);
        Assert.assertEquals(Long.valueOf(aSeatsList.size()), Long.valueOf(BookingUtils.getSeats(CoachType.AC)));
        trainService.deleteTrain(train.getId());

    }
}
