package org.myself.DAL;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

class BengreenDB {
    private final String url = "jdbc:mysql://localhost:3306/villagegreen?serverTimezone=UTC";
    private final String user = "root";
    private final String password = "";
    public Connection connection = null;

    public BengreenDB() throws SQLException {
    }

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        //System.out.println ("ça marche ");//to verify connection ok
        return connection;
    }

}
