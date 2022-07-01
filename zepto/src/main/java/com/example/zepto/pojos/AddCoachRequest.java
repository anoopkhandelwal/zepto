package com.example.zepto.pojos;

import com.example.zepto.enums.CoachType;
import lombok.Getter;

@Getter
public class AddCoachRequest {
    private Integer trainId;
    private CoachType coachType;
}
