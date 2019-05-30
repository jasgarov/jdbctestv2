package com.javid.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtil {
    static String JDBC_DRIVER;
    static String URL;
    static String HOST;
    static String DB;

    static String USER;
    static String PASS;

    static {
        try(InputStream input = new FileInputStream("src/main/resources/config.properties")){
            Properties properties = new Properties();

            properties.load(input);

            JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
            URL = properties.getProperty("URL");
            HOST = properties.getProperty("HOST");
            DB = properties.getProperty("DB");

            USER = properties.getProperty("USER");
            PASS = properties.getProperty("PASS");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(URL+HOST+DB, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
