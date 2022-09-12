package com.neusoft.elm.util;

import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/elm_admin?&useSSL=false&serverTimezone=UTC";
    private static final String PASSWORD = "Potato120";
    private static final String USER = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getDBConnection() {
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
        return con;
    }


    public static void close(Connection con, PreparedStatement pst, ResultSet rs) {
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
