package com.demo.doubledatasource.controller;

import com.demo.doubledatasource.config.CutDataSource;
import com.demo.doubledatasource.config.TransactionConfig;
import com.demo.doubledatasource.config.User;
import com.demo.doubledatasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    UserService userService;
    //查询数据源1
    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    //查询数据源2
    @GetMapping("/list2")
    public List list2(){return userService.list2();}

    //新增数据源1
    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.add(user);
        return "success";
    }

    //新增数据源2
    @PostMapping("/add2")
    public String add2(@RequestBody User user){
        userService.add2(user);
        return "success";
    }

    //测试同个请求插入不同数据源
    //@Transactional
    @GetMapping("/shiwuA")
    public String shiwuA(){
       userService.cutA();
        return "success";
    }
    @Transactional(value = TransactionConfig.TEST2_TX,rollbackFor = Exception.class)
    @GetMapping("/shiwuB")
    public String shiwuB(){
        userService.shiwu();
        return "success";
    }

}
