import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxF = 1500, minP = Integer.MAX_VALUE;
        int[] freq = new int[maxF + 1];
        for (int[] it : items) {
            freq[it[0]]++;
            minP = Math.min(minP, it[1]);
        }

        int[] gain = new int[maxF + 1];
        for (int f = 1; f <= maxF; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= maxF; m += f) gain[f] += freq[m];
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int[] it : items) {
            int p = it[1], g = gain[it[0]];
            for (int b = budget; b >= p; b--) {
                if (dp[b - p] != -1) dp[b] = Math.max(dp[b], dp[b - p] + g);
            }
        }

        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            if (dp[b] != -1) ans = Math.max(ans, dp[b] + (budget - b) / minP);
        }
        return ans;
    }
}