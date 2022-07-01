package com.example.zepto.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class Train {
    private UUID uuid;
    private Integer id;
    private List<Coach> coaches;
    private String from;
    private String to;
}
