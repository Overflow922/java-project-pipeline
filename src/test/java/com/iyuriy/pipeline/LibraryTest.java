package com.iyuriy.pipeline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LibraryTest {
    @Test
    public void testAddition() {
        assertEquals(4, (new Library()).addition(2, 2));
    }
}

