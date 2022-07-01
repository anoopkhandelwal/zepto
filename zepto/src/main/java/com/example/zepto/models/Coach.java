package com.example.zepto.models;

import com.example.zepto.enums.CoachType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class Coach {
    private UUID coachId;
    private Integer id;
    private CoachType coachType;
    private List<Seat> seats;
    private Integer trainId;
}
