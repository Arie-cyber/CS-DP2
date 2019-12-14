package com.example.cs_ia_0512;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static String ip = "arielcs.database.windows.net";
    private static String port = "1433";
    private static String classs = "net.sourceforge.jtds.jdbc.Driver";
    private static String db = "CSIA";
    private static String un = "ariel";
    private static String password = "Zigler1805";

    public static Connection connect() {
        Connection conn = null;
        String ConnURL = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
