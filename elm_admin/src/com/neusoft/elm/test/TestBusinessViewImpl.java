package com.neusoft.elm.test;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.po.Business;

public class TestBusinessViewImpl {


    private final Scanner input = new Scanner(System.in);

    @Test
    public void TestlistBusinessAll() {
        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(null, null);
        TestshowBusinessList(list);
    }

    @Test
    public void TestlistBusiness() {
        String businessName = "";
        String businessAddress = "";

        String inputStr ;
        System.out.println("是否需要输入商家名称关键词(y/n)：");
        inputStr = input.next();

        if (inputStr.equals("y")) {
            System.out.println("请输入商家名称关键词：");
            businessName = input.next();
        }

        System.out.println("是否需要输入商家地址关键词(y/n)：");
        inputStr = input.next();

        if (inputStr.equals("y")) {
            System.out.println("请输入商家地址关键词：");
            businessAddress = input.next();
        }

        BusinessDao dao = new BusinessDaoImpl();
        List<Business> list = dao.listBusiness(businessName, businessAddress);
        TestshowBusinessList(list);
    }

    @Test
    public void TestcreateBusiness() {
        System.out.println("请输入商家名称：");
        String businessName = input.next();
        BusinessDao dao = new BusinessDaoImpl();
        int businessID = dao.createBusiness(businessName);
        if (businessID > 0) {
            System.out.println("新建商家成功！商家编号为：" + businessID);
        } else {
            System.out.println("新建商家失败！");
        }
    }

    @Test
    public void TestshowBusinessList(List<Business> list) {
        System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
        for (Business b : list) {
            System.out.println(b.getBusinessID() + "\t" + b.getBusinessName() + "\t" + b.getBusinessAddress() + "\t" + b.getBusinessExplain() + "\t" + b.getStarPrice() + "\t" + b.getDeliveryPrice());
        }
    }

    @Test
    public void TestdeleteBusiness() {
        System.out.println("请输入商家编号：");
        int businessID = input.nextInt();

        BusinessDao dao = new BusinessDaoImpl();
        System.out.println("确认要删除吗(y/n)：");
        if (input.next().equals("y")) {
            int result = dao.deleteBusiness(businessID);
            if (result == 1) {
                System.out.println("删除商家成功！");
            } else {
                System.out.println("删除商家失败！");
            }
        }
    }

    @Test
    public void Testlogin() {
        System.out.println("请输入商家编号：");
        int businessID = input.nextInt();
        System.out.println("请输入密码：");
        String password = input.next();

        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessByIdByPass(businessID, password);
        System.out.println(business);
    }


    @Test
    public void TestupdateBusiness(Integer businessID) {//更新指定商家的信息
        //先将商家信息查询出来显示，然后用户才能更新
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessById(businessID);
        System.out.println(business+"\n");
        String inputStr ;
        System.out.println("是否修改商家名称(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的商家名称：");
            business.setBusinessName(input.next());
        }

        System.out.println("是否修改商家地址(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的商家地址：");
            business.setBusinessAddress(input.next());
        }

        System.out.println("是否修改商家介绍(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的商家介绍：");
            business.setBusinessExplain(input.next());
        }

        System.out.println("是否修改起送费(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的起送费：");
            business.setStarPrice(input.nextDouble());
        }

        System.out.println("是否修改配送费(y/n)：");
        inputStr = input.next();
        if (inputStr.equals("y")) {
            System.out.println("请输入新的配送费：");
            business.setDeliveryPrice(input.nextDouble());
        }

        int result = dao.updateBusiness(business);
        if (result > 0) {
            System.out.println("\n修改商家信息成功！\n");
        } else {
            System.out.println("\n修改商家信息失败！\n");
        }
    }

    @Test
    public void TestupdateBusinessByPassword(Integer businessID) {//更新商家密码
        BusinessDao dao = new BusinessDaoImpl();
        Business business = dao.getBusinessById(businessID);

        System.out.println("\n请输入旧密码：");
        String oldPass = input.next();
        System.out.println("\n请输入新密码：");
        String password = input.next();
        System.out.println("\n请再次输入新密码：");
        String beginPassword = input.next();

        if (!business.getPassword().equals(oldPass)) {
            System.out.println("\n旧密码输入错误！");
        } else if (!password.equals(beginPassword)) {
            System.out.println("\n两次输入密码不一致！");
        } else {
            int result = dao.updateBusinessByPassword(businessID, password);
            if (result > 0) {
                System.out.println("\n修改密码成功！");
            } else {
                System.out.println("\n修改密码失败！");
            }
        }
    }
}
