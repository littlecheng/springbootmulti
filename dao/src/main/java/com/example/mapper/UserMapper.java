package com.example.mapper;

import com.example.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>类中文名</b>
 * <p>类的描述，主要描述类的作用，使用说明，重要物性</p>
 *
 * @author 程涛(88396208)
 * @date 2018-12-25
 */
@Repository
public interface UserMapper {
    List<User> selectUsers();

    void insertUser(User user);
}
