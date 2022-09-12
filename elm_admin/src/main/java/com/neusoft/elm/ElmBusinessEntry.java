package com.neusoft.elm;


import com.neusoft.elm.po.Business;
import com.neusoft.elm.util.Util;
import com.neusoft.elm.view.FoodView;
import com.neusoft.elm.view.impl.BusinessViewImpl;
import com.neusoft.elm.view.impl.FoodViewImpl;

import java.util.Scanner;

public class ElmBusinessEntry {
    private final Scanner input = new Scanner(System.in);

    public void work() {
        Util.printElmLogo();

        BusinessViewImpl businessViewImpl = new BusinessViewImpl();

        Business business = businessViewImpl.login();

        if (business != null) {
            int menu = 0;

            while (menu != 5) {
                System.out.println("一级：--- 1-查看商家信息 2-修改商家信息 3-更新密码 4-食物管理（进入二级） 5-退出系统 ---");
                System.out.println("请输入你的选择");

                menu = input.nextInt();

                switch (menu) {
                    case 1:
                        System.out.println(business);
                        break;
                    case 2:
                        businessViewImpl.updateBusiness(business.getBusinessID());
                        break;
                    case 3:
                        businessViewImpl.updateBusinessByPassword(business.getBusinessID());
                        break;
                    case 4:
                        foodManager(business.getBusinessID());
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("输入选项错误！");
                }

            }
        } else {
            System.out.println("商家登录失败！");
        }
    }

    private void foodManager(Integer businessID) {
        FoodView foodView = new FoodViewImpl();

        int menu = 0;
        while (menu != 5) {
            System.out.println("二级：--- 1-查看食品列表 2-新增食品 3-修改食品 4-删除食品 5-返回一级菜单 ---");
            System.out.println("请输入你的选择");

            menu = input.nextInt();
            switch (menu) {
                case 1:
                    foodView.listFood(businessID);
                    break;
                case 2:
                    foodView.createFood(businessID);
                    break;
                case 3:
                    foodView.updateFood(businessID);
                    break;
                case 4:
                    foodView.deleteFood(businessID);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("输入选项错误！");

            }
        }
    }

}
