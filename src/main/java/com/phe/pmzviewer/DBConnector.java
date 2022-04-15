package com.phe.pmzviewer;

import java.sql.*;

public class DBConnector {
    private Connection con;

    public void ConnectToDB(){
        String connectionUrl =
                "jdbc:sqlserver://68.183.223.224;"
                        + "database=PZM;"
                        + "user=SA;"
                        + "password=DbServer2!;"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";


        try{
            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void closeConnection() {
        try {
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
