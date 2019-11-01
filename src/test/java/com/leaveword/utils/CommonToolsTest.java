package com.leaveword.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommonToolsTest {

    @Test
    public void isEmpty() {
        assertFalse(CommonTools.isEmpty("test"));
        assertTrue(CommonTools.isEmpty(""));
        assertTrue(CommonTools.isEmpty(null));
    }

    @Test
    public void getCurrentTime() {
        assertEquals(CommonTools.getCurrentTime(),CommonTools.getCurrentTime());

    }
}