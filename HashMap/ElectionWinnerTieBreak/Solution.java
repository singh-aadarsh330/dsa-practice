// User function Template for Java

import java.util.HashMap;

/**
 * Determines the candidate with the maximum votes.
 * In case of a tie, returns the lexicographically smallest name.
 */
class Solution {

    public static String TieBreak(String[] arr) {

        int n = arr.length;
        HashMap<String, Integer> cand = new HashMap<>();

        int maxVotes = 0;
        String winner = "";

        for (int i = 0; i < n; i++) {
            cand.put(arr[i], cand.getOrDefault(arr[i], 0) + 1);
            int votes = cand.get(arr[i]);

            if (votes > maxVotes) {
                maxVotes = votes;
                winner = arr[i];
            } else if (votes == maxVotes && arr[i].compareTo(winner) < 0) {
                winner = arr[i];
            }
        }

        return winner;
    }
}
