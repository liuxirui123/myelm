package com.neusoft.elm;

import com.neusoft.elm.po.Admin;
import com.neusoft.elm.util.Util;
import com.neusoft.elm.view.AdminView;
import com.neusoft.elm.view.BusinessView;
import com.neusoft.elm.view.impl.AdminViewImpl;
import com.neusoft.elm.view.impl.BusinessViewImpl;

import java.util.Scanner;

public class ElmAdminEntry {
    public void work() {

        Util.printElmLogo();//画出饿了么logo

        AdminView adminViewImpl = new AdminViewImpl();
        BusinessView businessViewImpl = new BusinessViewImpl();

        Admin admin = adminViewImpl.login();


        if (admin != null) {//登陆成功
            System.out.println("登录成功");
            int menu = 0;
            Scanner input = new Scanner(System.in);

            while (menu != 5) {

                System.out.println("\n----1.商家列表=2.搜索商家=3.新建商家=4.删除商家=5.退出系统----");
                System.out.println("请输入你的选项");
                menu = input.nextInt();//输入下一个选项
                switch (menu) {
                    case 1:
                        businessViewImpl.listBusinessAll();
                        break;
                    case 2:
                        businessViewImpl.listBusiness();
                        break;
                    case 3:
                        businessViewImpl.createBusiness();
                        break;
                    case 4:
                        businessViewImpl.deleteBusiness();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("输入选项错误！");
                }
            }
        } else {
            System.out.println("登录失败！");
        }
    }


}
