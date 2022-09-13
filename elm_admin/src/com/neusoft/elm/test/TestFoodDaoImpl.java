package com.neusoft.elm.test;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.neusoft.elm.po.Food;
import com.neusoft.elm.util.DBUtil;

public class TestFoodDaoImpl {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Test
    public void TestlistFood(Integer businessID) {
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
//        return list;
		for(Food food : list) {	
			System.out.println(food);
		}

    }

    @Test
    public void TestcreateFood(Food food) {
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
            
            if (result > 0) {
            	System.out.println("创建成功！");
            } else {
            	System.out.println("创建失败!");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, null);
        }
//        return result;
    }

    @Test
    public void TestgetFoodByID(Integer foodID) {
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
//        return food;
        System.out.println(food);
    }

    @Test
    public void TestupdateFood(Food food) {
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
            
            if (result > 0) {
            	System.out.println("更新成功！");
            } else {
            	System.out.println("更新失败!");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, null);
        }
//        return result;
    }

    @Test
    public void TestdeleteFood(Integer foodID) {
        int result = 0;
        String sql = "delete from food where foodId=?";
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, foodID);
            result = pst.executeUpdate();
            
            if (result > 0) {
            	System.out.println("删除成功！");
            } else {
            	System.out.println("删除失败!");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
//        return result;
    }

}
