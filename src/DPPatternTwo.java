public class DPPatternTwo {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x < amount + 1; ++x) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];
    }

    public int solveRodCutting(int[] lengths, int[] prices, int n) {

        if (n <= 0 || prices.length == 0 || prices.length != lengths.length)
            return 0;

        int lengthCount = lengths.length;
        int[][] dp = new int[lengthCount][n + 1];

        for(int i=0; i < lengthCount; i++) {
            for(int len=1; len <= n; len++) {
                int p1=0, p2=0;
                if(lengths[i] <= len)
                    p1 = prices[i] + dp[i][len-lengths[i]];
                if( i > 0 )
                    p2 = dp[i-1][len];
                dp[i][len] = Math.max(p1, p2);
            }
        }

        return dp[lengthCount-1][n];
    }


}
