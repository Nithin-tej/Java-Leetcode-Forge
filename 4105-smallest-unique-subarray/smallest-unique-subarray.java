import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length, low = 1, high = n, ans = n;
        long mod = 1_000_000_007L, base = 131_542_391L;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long h = 0, p = 1;
            for (int i = 0; i < mid - 1; i++) p = (p * base) % mod;
            for (int i = 0; i < mid; i++) h = (h * base + nums[i]) % mod;

            Map<Long, Integer> freq = new HashMap<>();
            freq.put(h, 1);

            for (int i = mid; i < n; i++) {
                h = ((h - nums[i - mid] * p % mod + mod) * base + nums[i]) % mod;
                freq.put(h, freq.getOrDefault(h, 0) + 1);
            }

            if (freq.containsValue(1)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}