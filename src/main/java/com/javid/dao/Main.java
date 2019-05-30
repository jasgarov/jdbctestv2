package com.javid.dao;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConnectionUtil.getConnection();
    }
}
