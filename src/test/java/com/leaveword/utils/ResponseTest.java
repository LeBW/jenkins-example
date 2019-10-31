package com.leaveword.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {
    Response response=new Response();
    @Test
    public void getStatus() {
        assertNull(response.getStatus());
    }

    @Test
    public void getContent() {
        assertNull(response.getContent());
    }

    @Test
    public void setStatus() {
        response.setStatus("-1");
        assertEquals("-1",response.getStatus());
    }

    @Test
    public void setContent() {
        response.setContent("一个对象");
        assertEquals("一个对象",response.getContent());
    }
}