package com.example.zepto.models;

import com.example.zepto.enums.SeatStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Seat {

    private UUID uuid;
    private Integer id;
    private SeatStatus status;

    // TODO can be referenced as foreign keys
    private UUID coachId;
    private Integer trainId;
}
