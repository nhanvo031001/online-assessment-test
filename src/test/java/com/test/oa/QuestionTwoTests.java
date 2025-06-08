package com.test.oa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class QuestionTwoTests {

    @Test
    public void testLongestSubstringWithoutRepeating() {
        String[][] testCases = {
                {"java2novice", "a2novice"},
                {"java_language_is_sweet", "uage_is"},
                {"", ""},
                {"abcabcbb", "abc"},
                {"abcdef", "abcdef"},
                {"aaaaa", "a"},
                {"a b c a b", "a b"},
                {"a", "a"},
                {"abba", "ab"},
                {"pwwkew", "wke"},
                {null, ""}
        };

        for (String[] testCase : testCases) {
            String input = testCase[0];
            String expected = testCase[1];
            String actual = QuestionTwo.longestSubstringWithoutRepeating(input);
            assertEquals(expected, actual, "Failed for input: " + input);
        }
    }
}
