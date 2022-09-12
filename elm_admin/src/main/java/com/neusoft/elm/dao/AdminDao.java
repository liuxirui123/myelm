package com.neusoft.elm.dao;

import com.neusoft.elm.po.Admin;

public interface AdminDao {

    Admin getAdminByNameByPass(String adminName, String password);//通过用户名和密码登录
}