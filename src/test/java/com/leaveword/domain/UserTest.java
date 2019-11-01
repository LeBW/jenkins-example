package com.leaveword.domain;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class UserTest {
    User user=new User();
    @Test
    public void getUserId() {
        assertEquals(0,user.getUserId());
    }

    @Test
    public void getUserName() {
       assertNull(user.getUserName());
    }

    @Test
    public void getUserPassword() {
        assertNull(user.getUserPassword());
    }

    @Test
    public void getRegisterTime() {
        assertNull(user.getRegisterTime());
    }
    @Test
    public void setUserId() {
        user.setUserId(999);
        assertEquals(999,user.getUserId());
    }

    @Test
    public void setUserName() {
        user.setUserName("test");
        assertEquals("test",user.getUserName());
    }

    @Test
    public void setUserPassword() {
        user.setUserPassword("123456");
        assertEquals("123456",user.getUserPassword());
    }

    @Test
    public void setRegisterTime() {
        Timestamp d = new Timestamp(System.currentTimeMillis());
        user.setRegisterTime(d);
        assertEquals(d,user.getRegisterTime());
    }
}