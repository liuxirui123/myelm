package com.neusoft.elm.test;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import com.neusoft.elm.dao.FoodDao;
import com.neusoft.elm.dao.impl.FoodDaoImpl;
import com.neusoft.elm.po.Food;

public class TestFoodViewImpl {

    Scanner input = new Scanner(System.in);

    @Test
    public void TestlistFood(Integer businessID) {

        FoodDao foodDao = new FoodDaoImpl();
        List<Food> list = foodDao.listFood(businessID);

        showListFood(list);
    }


    private void showListFood(List<Food> foodList) {
        System.out.println("食物编号\t食物名称\t食物介绍\t食物价格");
        for (Food food : foodList) {
            System.out.println(food.getFoodID() + "\t" + food.getFoodName() + "\t" + food.getFoodExplain() + "\t" + food.getFoodPrice());
        }
    }

    @Test
    public void TestcreateFood(Integer businessID) {
        Food food = new Food();
        System.out.println("请输入食品名称：");
        food.setFoodName(input.next());
        System.out.println("请输入食品介绍：");
        food.setFoodExplain(input.next());
        System.out.println("请输入食品价格：");
        food.setFoodPrice(input.nextDouble());
        food.setBusinessId(businessID);

        FoodDao foodDao = new FoodDaoImpl();
        int result = foodDao.createFood(food);
        if (result > 0) {
            System.out.println("\n新增食品成功！\n");
        } else {
            System.out.println("\n新增食品失败！\n");
        }
    }

    @Test
    public void TestupdateFood(Integer businessID) {
        FoodDao foodDao = new FoodDaoImpl();
        List<Food> list = foodDao.listFood(businessID);
        showListFood(list);

        if (list.size() == 0) {
            System.out.println("没有任何食品！");
        } else {
            System.out.println("请选择要更新的食品编号：");
            int foodId = input.nextInt();

            Food food = foodDao.getFoodByID(foodId);
            System.out.println(food);

            String inputStr ;
            System.out.println("是否更新食品名称(y/n)：");
            inputStr = input.next();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的食品名称：");
                food.setFoodName(input.next());
            }

            System.out.println("是否更新食品介绍(y/n)：");
            inputStr = input.next();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的食品介绍：");
                food.setFoodExplain(input.next());
            }

            System.out.println("是否更新食品价格(y/n)：");
            inputStr = input.next();
            if (inputStr.equals("y")) {
                System.out.println("请输入新的食品价格：");
                food.setFoodPrice(input.nextDouble());
            }

            int result = foodDao.updateFood(food);
            if (result > 0) {
                System.out.println("\n修改食品成功！\n");
            } else {
                System.out.println("\n修改食品失败！\n");
            }
        }
    }

    @Test
    public void TestdeleteFood(Integer businessID) {
        FoodDao foodDao = new FoodDaoImpl();
        List<Food> list = foodDao.listFood(businessID);
        showListFood(list);

        if (list.size() == 0) {
            System.out.println("没有任何食品！");
        } else {
            System.out.println("请选择要删除的食品编号：");
            int foodId = input.nextInt();

            System.out.println("确认要删除吗(y/n)：");
            if (input.next().equals("y")) {
                int result = foodDao.deleteFood(foodId);
                if (result > 0) {
                    System.out.println("\n删除食品成功！\n");
                } else {
                    System.out.println("\n删除食品失败！\n");
                }
            }
        }
    }
}
