package com.javid.dao;

import com.javid.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCRUD implements GenericDAO<User>{


    public boolean create(User user) {
        String insQ = "INSERT INTO USERS(login, password) VALUES (?, ?);";
        try (PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(insQ)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            int res = ps.executeUpdate();

            if (res != 1) {
                throw new Exception("problem with creating user!!!");
            }

        } catch (SQLException se) {

            System.out.println(se.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public User read(int id) {
        User user = null;
        String rQuery = "{CALL getUserById(?,?,?)}";
        try (CallableStatement cstm = ConnectionUtil.getConnection().prepareCall(rQuery)) {
            cstm.setInt(1, id);
            cstm.executeQuery();

            user = new User();
            user.setId(id);
            user.setLogin(cstm.getString(2));
            user.setPassword(cstm.getString(3));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public void update(User user) {
        String rQuery = "{CALL updateUserInfoById(?,?,?)}";
        try (CallableStatement cstm = ConnectionUtil.getConnection().prepareCall(rQuery)) {
            cstm.setInt(1, user.getId());
            cstm.setString(2, user.getLogin());
            cstm.setString(3, user.getPassword());
            cstm.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String rQuery = "{CALL updateUserInfoById(?)}";
        try (CallableStatement cstm = ConnectionUtil.getConnection().prepareCall(rQuery)) {
            cstm.setInt(1, id);
            cstm.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        String rQuery = "SELECT * FROM users";
        try (Statement stm = ConnectionUtil.getConnection().createStatement()) {
            ResultSet rs = stm.executeQuery(rQuery);


            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}