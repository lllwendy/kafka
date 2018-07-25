package com.kafka.demo.entity;

import java.io.Serializable;

/**
 * @author  javawendy(lll)
 * @create  2018/7/20 12:47
 * @desc
 **/

public class User implements Serializable {
    private static final long serialVersionUID = 3328101427967321282L;

    private  Integer  id;
    private  String   userName;
    private  String   userPassword;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
