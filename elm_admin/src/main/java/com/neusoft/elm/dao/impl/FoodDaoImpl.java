package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.FoodDao;
import com.neusoft.elm.po.Food;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Food> listFood(Integer businessID) {
        List<Food> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from business where businessID=? ");


        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql.toString());
            pst.setInt(1, businessID);
            rs = pst.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodID(rs.getInt("foodID"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setBusinessId(businessID);
                list.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        return list;

    }

    @Override
    public Integer createFood(Food food) {
        int result = 0;
        String sql = "insert into food values(null,?,?,?,?)";
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, food.getFoodName());
            pst.setString(2, food.getFoodExplain());
            pst.setDouble(3, food.getFoodPrice());
            pst.setInt(4, food.getBusinessId());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, null);
        }
        return result;
    }

    @Override
    public Food getFoodByID(Integer foodID) {
        Food food = null;
        String sql = "select * from food where foodId=?";
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, foodID);
            rs = pst.executeQuery();
            while (rs.next()) {
                food = new Food();
                food.setFoodID(foodID);
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setBusinessId(rs.getInt("businessId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        return food;
    }

    @Override
    public Integer updateFood(Food food) {
        int result = 0;
        String sql = "update food set foodName=?,foodExplain=?,foodPrice=? where foodId=?";
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, food.getFoodName());
            pst.setString(2, food.getFoodExplain());
            pst.setDouble(3, food.getFoodPrice());
            pst.setInt(4, food.getFoodID());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, null);
        }
        return result;
    }

    public Integer deleteFood(Integer foodID) {
        int result = 0;
        String sql = "delete from food where foodId=?";
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, foodID);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        return result;
    }

}
