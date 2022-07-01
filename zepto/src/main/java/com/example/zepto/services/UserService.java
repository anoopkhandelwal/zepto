package com.example.zepto.services;

import com.example.zepto.daos.UserDAO;
import com.example.zepto.exceptions.NotFoundException;
import com.example.zepto.models.User;
import com.example.zepto.pojos.AddUserRequest;
import com.example.zepto.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User getUser(Integer id) throws NotFoundException {
        User user = userDAO.get(id);
        if(user == null){
            throw new NotFoundException(Constants.USER_NOT_FOUND);
        }else{
            return user;
        }
    }

    public User createUser(AddUserRequest request) {
        return userDAO.create(request.getName(),
                request.getAddress(),
                request.getGender(),
                request.getUserType(),
                request.getPinCode(),
                request.getAge());
    }

    public boolean isAdminUser(UUID uuid) throws NotFoundException {
        Boolean status = userDAO.isAdmin(uuid);
        if(status==null){
            throw new NotFoundException(Constants.USER_NOT_FOUND);
        }
        return status;
    }

    public boolean isAdminUser(Integer id) throws NotFoundException {
        Boolean status = userDAO.isAdmin(id);
        if(status==null){
            throw new NotFoundException(Constants.USER_NOT_FOUND);
        }
        return status;
    }

    public void isAdmin(Integer id) throws NotFoundException {
        Boolean isAdmin = isAdminUser(id);
        if(!isAdmin){
            throw new NotFoundException(Constants.USER_NOT_ADMIN);
        }
    }

    public void isAdmin(String uuid) throws NotFoundException {
        Boolean isAdmin = isAdminUser(UUID.fromString(uuid));
        if(!isAdmin){
            throw new NotFoundException(Constants.USER_NOT_ADMIN);
        }
    }

}
