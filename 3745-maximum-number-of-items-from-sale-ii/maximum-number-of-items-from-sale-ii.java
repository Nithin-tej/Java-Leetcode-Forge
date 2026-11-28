import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1], div = new int[n + 1];
        long minP = Long.MAX_VALUE;

        for (int[] it : items) {
            freq[it[0]]++;
            minP = Math.min(minP, it[1]);
        }

        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) div[f] += freq[m];
        }

        long[][] arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = items[i][1];
            arr[i][1] = div[items[i][0]] - 1;
        }

        Arrays.sort(arr, Comparator.comparingLong(a -> a[0]));

        long rem = budget, ans = 0;
        for (long[] it : arr) {
            if (it[0] >= 2 * minP) break;
            long take = Math.min(it[1], rem / it[0]);
            ans += 2 * take;
            rem -= take * it[0];
        }

        return (int) (ans + rem / minP);
    }
}