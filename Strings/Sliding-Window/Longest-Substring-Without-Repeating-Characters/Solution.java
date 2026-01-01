import java.util.HashMap;

class Solution {

    /**
     * Returns the length of the longest substring without repeating characters.
     *
     * Approach:
     * Sliding Window using HashMap
     *
     * The HashMap stores the last index of each character.
     * When a duplicate is found within the current window,
     * the left pointer is moved forward safely.
     */
    public int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If character already exists in the current window
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // Update last seen index
            map.put(currentChar, right);

            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
