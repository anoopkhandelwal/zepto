package com.example.zepto.daos;

import com.example.zepto.models.Train;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TrainDAO {

    private static List<Train> trains = new ArrayList<>();
    private Integer TRAINS_COUNTER = 1;

    public Train get(UUID uuid){
        for(Train train : trains){
            if(train.getUuid().equals(uuid)){
                return train;
            }
        }
        return null;
    }

    public Train get(Integer id){
        for(Train train : trains){
            if(train.getId().equals(id)){
                return train;
            }
        }
        return null;
    }

    public Train create(String from ,String to){
        Train train = Train.builder()
                .uuid(UUID.randomUUID())
                .id(TRAINS_COUNTER++)
                .from(from)
                .to(to)
                .coaches(new ArrayList())
                .build();
        trains.add(train);
        return train;
    }

    public int delete(UUID uuid){
        for(Train train : trains){
            if(train.getUuid().equals(uuid)){
                trains.remove(train);
                return 1;
            }
        }
        return -1;
    }

    public int delete(Integer id){
        for(Train train : trains){
            if(train.getId().equals(id)){
                trains.remove(train);
                return 1;
            }
        }
        return -1;
    }

    public List<Train> getAll(){
        return trains;
    }
}
