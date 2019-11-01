package com.leaveword.domain;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class WordTest {
    Word word=new Word();
    @Test
    public void getWordId() {
        assertEquals(0,word.getWordId());
    }

    @Test
    public void getUserId() {
        assertEquals(0,word.getUserId());
    }

    @Test
    public void getTitle() {
        assertNull(word.getTitle());
    }

    @Test
    public void getContent() {
        assertNull(word.getContent());
    }

    @Test
    public void getLeaveTime() {
        assertNull(word.getLeaveTime());
    }

    @Test
    public void setWordId() {
        word.setWordId(999);
        assertEquals(999,word.getWordId());
    }

    @Test
    public void setUserId() {
        word.setUserId(999);
        assertEquals(999,word.getUserId());
    }

    @Test
    public void setTitle() {
        word.setTitle("test");
        assertEquals("test",word.getTitle());
    }

    @Test
    public void setContent() {
        word.setContent("test");
        assertEquals("test",word.getContent());
    }

    @Test
    public void setLeaveTime() {
        Timestamp d = new Timestamp(System.currentTimeMillis());
        word.setLeaveTime(d);
        assertEquals(d,word.getLeaveTime());
    }
}