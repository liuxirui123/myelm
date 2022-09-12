package com.neusoft.elm.dao;

import com.neusoft.elm.po.Food;

import java.util.List;

public interface FoodDao {
    List<Food> listFood(Integer businessID);

    Integer createFood(Food food);

    Food getFoodByID(Integer foodID);

    Integer updateFood(Food food);
    Integer deleteFood(Integer foodID);
    }
