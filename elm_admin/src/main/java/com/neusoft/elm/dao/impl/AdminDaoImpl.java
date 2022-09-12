package com.neusoft.elm.dao.impl;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {

    private Connection con = null;//数据库连接
    private PreparedStatement pst = null;//预编译语句
    private ResultSet rs = null;

    @Override
    public Admin getAdminByNameByPass(String adminName, String password) {
        Admin admin = null;
        String sql = "select * from admin where adminName=? and password=?";//sql查询语句
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, adminName);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while (rs.next()) {//查询到数据 且由于adminName的唯一性 只会查到一个
                admin = new Admin();
                admin.setAdminID(rs.getInt("adminId"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
        return admin;
    }
}