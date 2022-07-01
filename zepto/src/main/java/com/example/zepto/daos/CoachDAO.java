package com.example.zepto.daos;
import com.example.zepto.enums.CoachType;
import com.example.zepto.enums.SeatStatus;
import com.example.zepto.models.Coach;
import com.example.zepto.models.Seat;
import com.example.zepto.utils.BookingUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoachDAO {
    private static List<Coach> coaches = new ArrayList<>();
    private static SeatDAO seatDAO = new SeatDAO();
    Integer COACHES_COUNTER = 1;

    public Coach get(UUID id){
        for(Coach coach : coaches){
            if(coach.getCoachId().equals(id)){
                return coach;
            }
        }
        return null;
    }

    public Coach get(Integer id){
        for(Coach coach : coaches){
            if(coach.getId().equals(id)){
                return coach;
            }
        }
        return null;
    }

    public Coach create(CoachType coachType,Integer trainId){
        Coach coach = Coach.builder()
                .coachId(UUID.randomUUID())
                .id(COACHES_COUNTER++)
                .coachType(coachType)
                .trainId(trainId)
                .seats(new ArrayList<>())
                .build();
        coach.setSeats(createSeats(coach));
        coaches.add(coach);
        return coach;
    }

    public List<Seat> createSeats(Coach coach){
        List<Seat> seats = new ArrayList<>();
        Integer numberOfSeats = BookingUtils.getSeats(coach.getCoachType());
        for(int i=0;i<numberOfSeats;i++){
            seats.add(seatDAO.create(coach,coach.getTrainId()));
        }
        return seats;
    }

    public int delete(UUID id) {
        for(Coach coach : coaches){
            if(coach.getCoachId().equals(id)) {
                for(Seat seat:coach.getSeats()){
                    seatDAO.delete(seat.getUuid());
                }
                coach.setSeats(null);
                coaches.remove(coach);
                return 1;
            }
        }
        return -1;
    }

    public int delete(Integer id){
        for(Coach coach : coaches){
            if(coach.getId().equals(id)){
                for(Seat seat:coach.getSeats()){
                    seatDAO.delete(seat.getId());
                }
                coach.setSeats(null);
                coaches.remove(coach);
                return 1;
            }
        }
        return -1;
    }

    public Seat getCoachSeat(Coach coach,UUID seatId){
        for(Seat seat : coach.getSeats()){
            if(seat.getUuid().equals(seatId) && SeatStatus.AVAILABLE.equals(seat.getStatus())){
                return seat;
            }
        }
        return null;
    }

    public Seat getCoachSeat(Coach coach,Integer seatId){
        for(Seat seat : coach.getSeats()){
            if(seat.getId().equals(seatId) && SeatStatus.AVAILABLE.equals(seat.getStatus())){
                return seat;
            }
        }
        return null;
    }

    public List<Seat> getCoachSeats(Coach coach, SeatStatus status){

        List<Seat> seats = new ArrayList<>();
        for(Seat seat:coach.getSeats()){
            if (seat.getStatus().equals(status)){
                seats.add(seat);
            }
        }
        return seats;
    }

}
