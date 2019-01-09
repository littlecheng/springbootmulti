package com.example;

import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <b>类中文名</b>
 * <p>类的描述，主要描述类的作用，使用说明，重要物性</p>
 *
 * @author 程涛(88396208)
 * @date 2018-12-25
 */
@Service
public class UserService {

    @Autowired
    UserMapper userDAO;

    public List<User> selectUsers(){
        return userDAO.selectUsers();
    }

    public void insertUser(User user){
        userDAO.insertUser(user);
    }
}
