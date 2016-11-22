package com.mkrt4an.service;

import com.mkrt4an.dao.UserDao;
import com.mkrt4an.entity.TruckEntity;
import com.mkrt4an.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 28.09.2016.
 */

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    //Find by id
    public UserEntity findById(Integer id) {
        return userDao.findById(id);
    }

    //Find by id
    public UserEntity findById(String id) {
        return userDao.findById(Integer.parseInt(id));
    }

    // Find all users
    public List<UserEntity> findAllUsers (){
        return userDao.getAll();
    }
}
