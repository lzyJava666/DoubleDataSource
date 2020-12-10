package com.demo.doubledatasource.config;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String name;
}
