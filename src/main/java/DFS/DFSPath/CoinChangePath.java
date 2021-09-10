package DFS.DFSPath;

import java.util.Arrays;

/**
 * 322. Coin Change
 */
public class CoinChangePath {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        int[] parent = new int[amount +1];
        Arrays.fill(parent, -1);

        dp[0] = 0;
        for (int a = 1; a <= amount; a++) {
            for (int j = 0; j < coins.length; j++) {
                if (a >= coins[j]) {
                    if(dp[a] >  dp[a - coins[j]] + 1) {
                        dp[a] = Math.min(dp[a], dp[a - coins[j]] + 1);
                        parent[a] = coins[j];
                    }
                }
            }
        }

        //restore path
        restorePath(parent, amount);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    private void restorePath(int[] parent, int amount) {
        if (parent[amount] != -1) {
            restorePath(parent, amount - parent[amount]);
            System.out.println(" amount " + parent[amount]);
        }
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 13;
        CoinChangePath test = new CoinChangePath();
        test.coinChange(coins, amount);
    }
}
