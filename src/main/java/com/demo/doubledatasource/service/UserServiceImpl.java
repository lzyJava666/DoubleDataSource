package com.demo.doubledatasource.service;

import com.demo.doubledatasource.config.CutDataSource;
import com.demo.doubledatasource.config.TargetDataSource;
import com.demo.doubledatasource.config.TransactionConfig;
import com.demo.doubledatasource.config.User;
import com.demo.doubledatasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.selectAll();
    }

    @TargetDataSource(name = "test2")
    @Override
    public List list2() {
        return userMapper.selectAll();
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @TargetDataSource(name = "test2")
    @Override
    public void add2(User user) {
        userMapper.insert(user);
    }

    @Transactional(value = TransactionConfig.TEST2_TX,rollbackFor = Exception.class)
    @TargetDataSource(name = "test2")
    @Override
    public void shiwu() {
        //CutDataSource.useDataSource("test2");
        User user=new User();
        user.setName("事务插入测试1");
        userMapper.insert(user);

        User u=new User();
        //u.setName("12");
        userMapper.insert(u);
    }

    @Override
    public void cutA() {
        User user=new User();
        user.setName("事务插入测试2221");
        userMapper.insert(user);
        CutDataSource.useDataSource("test2");
        User u=new User();
        u.setName("tttttt");
        userMapper.insert(u);
    }
}
