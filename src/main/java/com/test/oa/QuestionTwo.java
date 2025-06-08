package com.test.oa;

import java.util.HashMap;
import java.util.Map;

public class QuestionTwo {
    /**
     * Finds the longest substring without repeating characters in a given string.
     *
     * @param s the input string
     * @return the longest substring without repeating characters
     */
    public static String longestSubstringWithoutRepeating(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        Map<Character, Integer> lastSeenIndex = new HashMap<>();
        int start = 0;
        int maxLength = 0;
        int maxSubstringStart = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // If the character was seen and is within the current window, move start
            if (lastSeenIndex.containsKey(currentChar) && lastSeenIndex.get(currentChar) >= start) {
                start = lastSeenIndex.get(currentChar) + 1;
            }

            // Update the last seen index for the current character
            lastSeenIndex.put(currentChar, end);

            // Update the max substring length and start index if necessary
            int currentLength = end - start + 1;
            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxSubstringStart = start;
            }
        }

        return s.substring(maxSubstringStart, maxSubstringStart + maxLength);
    }
}
