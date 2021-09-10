package DFS.DFSPath;

/**
 * https://leetcode.com/problems/edit-distance/
 * */
public class EditDistanceAndGetPath {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() +1][word2.length() +1];

        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }

        //dp[0][0] =1;
        for(int i = 1; i <= word1.length(); i++) {
            for(int j = 1; j <= word2.length(); j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) +1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        EditDistanceAndGetPath test = new EditDistanceAndGetPath();
        test.minDistance(word1, word2);
    }
}
