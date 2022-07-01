package com.example.zepto.daos;

import com.example.zepto.enums.Gender;
import com.example.zepto.enums.UserType;
import com.example.zepto.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {
    private static List<User> users = new ArrayList<>();
    private static Integer USERS_COUNTER = 1;

    public User get(UUID uuid) {
        for(User user : users) {
            if(user.getUuid().equals(uuid)){
                return user;
            }
        }
        return null;
    }

    public User get(Integer id){
        for(User user : users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User create(String name,
                       String address,
                       Gender gender,
                       UserType userType,
                       Long pinCode,
                       Integer age) {

        User user = User.builder().uuid(UUID.randomUUID())
                .id(USERS_COUNTER++)
                .age(age)
                .address(address)
                .gender(gender)
                .userType(userType)
                .pinCode(pinCode)
                .name(name)
                .build();

        users.add(user);
        return user;
    }

    public Boolean isAdmin(UUID uuid){

        for(User user : users){
            if(user.getUuid().equals(uuid)){
                return user.getUserType().equals(UserType.ADMIN);
            }
        }
        return null;
    }

    public Boolean isAdmin(Integer id){

        for(User user : users){
            if(user.getId().equals(id)){
                return user.getUserType().equals(UserType.ADMIN);
            }
        }
        return null;
    }

    public int delete(UUID uuid){
        for(User user : users){
            if(user.getUuid().equals(uuid)){
                users.remove(user);
                return 1;
            }
        }
        return -1;
    }

}
