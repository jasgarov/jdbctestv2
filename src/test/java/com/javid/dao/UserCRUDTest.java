package com.javid.dao;

import com.javid.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCRUDTest {

    private UserCRUD ucrd;


    @Before
    public void setUp() throws Exception {
        ucrd = new UserCRUD();
    }

    @Test
    public void create() {
        User u = new User();
        u.setLogin("thor");
        u.setPassword("odinson");

        boolean res= ucrd.create(u);



    }

    @Test
    public void read() {
        GenericDAO<User> dao = new UserCRUD();

        User someUser = dao.read(5);
        assertEquals("thor", someUser.getLogin());

        System.out.println(someUser);


    }





    @Test
    public void update() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void getAll() {
    }
}