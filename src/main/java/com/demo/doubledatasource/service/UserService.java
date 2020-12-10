package com.demo.doubledatasource.service;

import com.demo.doubledatasource.config.User;

import java.util.List;

public interface UserService {
    List<User> list();

    List list2();

    void add(User user);

    void add2(User user);

    void shiwu();

    void cutA();
}
