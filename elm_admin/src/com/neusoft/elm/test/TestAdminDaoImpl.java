package com.neusoft.elm.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import com.neusoft.elm.util.DBUtil;


public class TestAdminDaoImpl {
	
	@Test
	public void TestgetAdminByNameByPass() {
		
		String sql = "select * from admin where adminName=? and password=?";//sql查询语句
	    Connection con = null;//数据库连接
	    PreparedStatement pst = null;//预编译语句
	    ResultSet rs = null;
        
        try {
            con = DBUtil.getDBConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {//查询到数据 且由于adminName的唯一性 只会查到一个
                int adminId = rs.getInt("adminId");
                String adminName = rs.getString("adminName");
                String password = rs.getString("password");
                System.out.println("Id:"+adminId+"Name:"+adminName+"pw:"+password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, pst, rs);
        }
	}

}
