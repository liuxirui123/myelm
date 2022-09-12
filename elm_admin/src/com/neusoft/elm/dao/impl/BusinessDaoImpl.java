package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    @Override
    public List<Business> listBusiness(String businessName, String businessAddress) {
        List<Business> list = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select * from business where 1=1 ");

        if (businessName != null && !businessName.equals("")) {
            sql.append(" and businessName like '%").append(businessName).append("%' ");//模糊查询 包含Name即可
        }
        if (businessAddress != null && !businessAddress.equals("")) {
            sql.append(" and businessAddress like '%").append(businessAddress).append("%' ");
        }

        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                Business business = new Business();
                business.setBusinessID(rs.getInt("businessID"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("startPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                list.add(business);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        return list;
    }

    @Override
    public int createBusiness(String businessName) {//新建商家
        int businessID = 0;
        String sql = "insert into business(businessName,password) values(?,'123')";
        try {
            con = DBUtil.getDBConnection();
            //设置返回自增长列值
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, businessName);
            pst.executeUpdate();
            //获取自增长列值（一行一列）
            rs = pst.getGeneratedKeys();
            if (rs.next()) {
                businessID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        return businessID;
    }

    @Override
    public int deleteBusiness(int businessID) {
        int result ;
        String delFoodSql = "delete from food where businessID=?";
        String delBusinessSql = "delete from business where businessID=?";
        try {
            con = DBUtil.getDBConnection();

            //开启一个事务
            con.setAutoCommit(false);

            pst = con.prepareStatement(delFoodSql);
            pst.setInt(1, businessID);
            pst.executeUpdate();

            pst = con.prepareStatement(delBusinessSql);
            pst.setInt(1, businessID);
            result = pst.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            result = 0;
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, null);
        }
        return result;
    }

    @Override
    public Business getBusinessByIdByPass(Integer businessID, String password) {//business调用 需要ID password
        if (checkBusinessPassword(businessID, password)) {
            return getBusinessById(businessID);
        }
        return null;
    }

    @Override
    public boolean checkBusinessPassword(Integer businessID, String password) {//检查用户的密码是否正确
        String sql = "select password from business where businessID=?";
        String passwordSearch = null;
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, businessID);
            rs = pst.executeQuery();
            while (rs.next()) {
                passwordSearch = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        if (passwordSearch == null || passwordSearch.equals("")) {
            return false;
        }
        return passwordSearch.equals(password);
    }

    @Override
    public Business getBusinessById(Integer businessID) {//admin调用 只需ID
        Business business = null;
        String sql = "select * from business where businessID=?";
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, businessID);
            rs = pst.executeQuery();
            while (rs.next()) {
                business = new Business();
                business.setBusinessID(rs.getInt("businessID"));
                business.setPassword(rs.getString("password"));
                business.setBusinessName(rs.getString("businessName"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        return business;
    }

    @Override
    public int updateBusiness(Business business) {
        int result = 0;
        String sql = "update business set businessName=?,businessAddress=?,businessExplain=?,starPrice=?,deliveryPrice=? where businessID=?";
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, business.getBusinessName());
            pst.setString(2, business.getBusinessAddress());
            pst.setString(3, business.getBusinessExplain());
            pst.setDouble(4, business.getStarPrice());
            pst.setDouble(5, business.getDeliveryPrice());
            pst.setInt(6, business.getBusinessID());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, null);
        }
        return result;
    }

    @Override
    public int updateBusinessByPassword(Integer businessID, String password) {
        int result = 0;
        String sql = "update business set password=? where businessID=?";
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, password);
            pst.setInt(2, businessID);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        return result;
    }
}