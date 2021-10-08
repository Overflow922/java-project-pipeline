package com.iyuriy.pipeline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {
    @Test
    public void testAddition() {
        assertEquals(4, (new Library()).addition(2, 2));
    }

    @Test
    public void testSubstraction() {
        assertEquals(0, (new Library()).substraction(1, 1));
    }
}

