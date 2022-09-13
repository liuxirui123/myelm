package com.neusoft.elm.test;

import java.util.Scanner;

import org.junit.Test;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.dao.impl.AdminDaoImpl;
import com.neusoft.elm.po.Admin;

public class TestAdminViewImpl {

    private final Scanner input = new Scanner(System.in);

    @Test
    public void Testlogin() {
        System.out.println("请输入管理员名称：");
        String adminName = input.next();
        System.out.println("请输入密码：");
        String password = input.next();

        AdminDao adminDao = new AdminDaoImpl();
        Admin admin = adminDao.getAdminByNameByPass(adminName, password);
        System.out.println(admin);
    }
}
