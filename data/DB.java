package kz.aitu.data;

import kz.aitu.data.interfaces.IDB;
import java.sql.*;
import java.sql.DriverManager;

public class DB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5433/assignment3";
        try {
            // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "0000");

            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
