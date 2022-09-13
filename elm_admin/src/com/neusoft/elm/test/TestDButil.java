package com.neusoft.elm.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestDButil {

    private static final String URL = "jdbc:mysql://localhost:3306/elm_admin?&useSSL=false&serverTimezone=UTC";
    private static final String PASSWORD = "Potato120";
    private static final String USER = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    @Test
    public void TestgetDBConnection() {
        Connection con = null;

        try {
            //注册JDBC驱动程序
            Class.forName(DRIVER);
            //建立连接
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
//        return con;
        System.out.println(con);
    }

    @Test
    public void Testclose(Connection con, PreparedStatement pst, ResultSet rs) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            con = null;

        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            pst = null;

        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            rs = null;

        }
    }
}
