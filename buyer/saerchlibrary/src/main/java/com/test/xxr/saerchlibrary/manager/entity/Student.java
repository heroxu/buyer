package com.test.xxr.saerchlibrary.manager.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * create by xuxiarong on 2018/4/11
 */
@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private int age;
    private String num;
}