import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        int maxVal = nums[nums.length - 1];
        // dp[v] stores the max length of a consecutive sequence ending at value v
        int[] dp = new int[maxVal + 2];
        int maxLen = 0;
        
        for (int x : nums) {
            // Case 1: Increment x to x + 1 (must be evaluated first)
            dp[x + 1] = Math.max(dp[x + 1], dp[x] + 1);
            
            // Case 2: Keep x as x
            dp[x] = Math.max(dp[x], dp[x - 1] + 1);
            
            maxLen = Math.max(maxLen, Math.max(dp[x], dp[x + 1]));
        }
        
        return maxLen;
    }
}