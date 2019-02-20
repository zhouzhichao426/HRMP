package com.company.hrm.dao.dbutil;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtil {
    public static ComboPooledDataSource dataSourse = new ComboPooledDataSource();
    public static Connection getConnection() throws Exception {
        Connection con = dataSourse.getConnection();
        return con;
    }
    public static void closeConnection(ResultSet rs, Statement ps, Connection con) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }

}
