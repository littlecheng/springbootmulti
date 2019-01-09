package com.example.controller;

import com.example.User;
import com.example.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * <b>类中文名</b>
 * <p>类的描述，主要描述类的作用，使用说明，重要物性</p>
 *
 * @author 程涛(88396208)
 * @date 2018-12-24
 */
@RestController
@RequestMapping("/")
public class indexController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String test(){
        return "test success";
    }

    @RequestMapping(value="/getUsers",method = RequestMethod.GET)
    public List<User> getAllUsers(){
       return userService.selectUsers();
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public boolean addUsers(@RequestParam("NO") String NO,@RequestParam("name") String name){
        User user = new User();
        user.setNO(NO);
        user.setName(name);
        userService.insertUser(user);
        return true;
    }

    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        // 以前的循环方式
        /*for (String player : players) {
            System.out.print(player + "; ");
        }*/

        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.print(player + "; "));
        System.out.println();

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);
    }
}
