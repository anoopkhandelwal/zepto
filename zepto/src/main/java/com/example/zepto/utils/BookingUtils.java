package com.example.zepto.utils;

import com.example.zepto.enums.CoachType;

import java.util.HashMap;
import java.util.Map;

public class BookingUtils {
    private static Map<CoachType,Integer> coachTypeSeatMapping = new HashMap();
    static {
        coachTypeSeatMapping.put(CoachType.AC,60);
        coachTypeSeatMapping.put(CoachType.NON_AC,60);
        coachTypeSeatMapping.put(CoachType.SEATER,120);

    }

    public static Integer getSeats(CoachType coachType){
     return coachTypeSeatMapping.containsKey(coachType)?coachTypeSeatMapping.get(coachType):null;
    }
}
