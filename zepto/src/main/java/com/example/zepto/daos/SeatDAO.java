package com.example.zepto.daos;

import com.example.zepto.enums.SeatStatus;
import com.example.zepto.models.Coach;
import com.example.zepto.models.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SeatDAO {
    private static List<Seat> seats = new ArrayList<>();
    Integer SEATS_COUNTER = 1;

    public Seat get(UUID uuid){
        for(Seat seat : seats){
            if(seat.getUuid().equals(uuid)){
                return seat;
            }
        }
        return null;
    }

    public Seat get(Integer id){
        for(Seat seat : seats){
            if(seat.getId().equals(id)){
                return seat;
            }
        }
        return null;
    }

    public Seat create(Coach coach,Integer trainId){
        Seat seat = Seat.builder().
                uuid(UUID.randomUUID())
                .id(SEATS_COUNTER++)
                .coachId(coach.getCoachId())
                .status(SeatStatus.AVAILABLE)
                .trainId(trainId)
                .build();
        seats.add(seat);
        return seat;
    }


    public int delete(UUID uuid){
        for(Seat seat : seats){
            if(seat.getUuid().equals(uuid)){
                seats.remove(seat);
                return 1;
            }
        }
        return -1;
    }

    public int delete(Integer id){
        for(Seat seat : seats){
            if(seat.getId().equals(id)){
                seats.remove(seat);
                return 1;
            }
        }
        return -1;
    }

    public List<Seat> getSeats(){
        return seats;
    }

    public Seat bookSeat(Seat seat){
        seat.setStatus(SeatStatus.BOOKED);
        return seat;
    }
}
