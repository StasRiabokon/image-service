package com.nau.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    private static Connection con;

    public static void createConnection(String dbUrl, String dbusername, String dbPassword) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, dbusername, dbPassword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }


    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
