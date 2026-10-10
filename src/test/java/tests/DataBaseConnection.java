package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static final String URL =
            "jdbc:postgresql://ep-broad-term-b4c0mgv2-pooler.c-6.us-east-2.aws.neon.tech:5432/neondb?sslmode=require&" +
                    "channel_binding=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_IpGF2CrEsM9Y";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

