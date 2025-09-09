package org.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testAreLettersAscending() {
        assertTrue(Main.areLettersAscending(""));
        assertTrue(Main.areLettersAscending("abc"));
        assertTrue(Main.areLettersAscending("a"));
        assertTrue(Main.areLettersAscending("xyz"));

        assertFalse(Main.areLettersAscending("cba"));
        assertFalse(Main.areLettersAscending("abdc"));
        assertFalse(Main.areLettersAscending("hello"));
        assertFalse(Main.areLettersAscending("zxc"));
    }

    @Test
    void testHandleLine() {
        String line = "abcdef fgas cdas abcd";
        ArrayList<String> result = Main.handleLine(line);
        String[] expected = {"abcdef", "abcd"};
        assertArrayEquals(expected, result.toArray(new String[0]));
    }
    @Test
    void testSplitLineToWords() {
        String line = "abc def   ghi\tjkl\nmno";
        String[] words = Main.splitLineToWords(line);

        String[] expected = {"abc", "def", "ghi", "jkl", "mno"};
        assertArrayEquals(expected, words);
    }
}