package com.javid.domain.sub;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class WriteToConfig {
    public static void main(String[] args) {
        try(OutputStream output = new FileOutputStream("src/main/resources/config.properties")){
            Properties prop = new Properties();

            prop.setProperty("JDBC_DRIVER", "com.mysql.cj.jdbc.Driver");
            prop.setProperty("URL", "jdbc:mysql://");
            prop.setProperty("HOST", "localhost/");
            prop.setProperty("DB", "budget_system");

            prop.setProperty("USER", "root");
            prop.setProperty("PASS", "root");

            prop.store(output, null);

            System.out.println(prop);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
