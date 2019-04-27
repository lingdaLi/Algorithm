public class MaxSumOfThreeSubarray {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if(nums.length < k * 3) return new int[0];

        int[] windowValue = new int[nums.length - k + 1];
        int sum = 0;
        for(int i = 0; i < windowValue.length; i++) {
            sum += nums[i];

            if(i >= k) sum -= nums[i-k];
            if(i >= k-1) windowValue[i-k+1] = sum;
        }

        int[] left = new int[windowValue.length];
        int max = 0;
        for(int i = 0; i < windowValue.length; i++) {
            if(windowValue[i] > windowValue[max]) {
                max = i;
            }

            left[i] = max;
        }

        int[] right = new int[windowValue.length];
        max = windowValue.length - 1;
        for(int i = windowValue.length - 1; i >= 0; i--) {
            if(windowValue[i] > windowValue[max]) {
                max = i;
            }

            right[i] = max;
        }

        int[] res = new int[3];
        max = 0;
        for(int i = k; i < windowValue.length - k + 1; i++) {
            int total = windowValue[left[i-k]] + windowValue[i] + windowValue[right[i+k]];
            if(total > max) {
                res[0] = left[i-k];
                res[1] = i;
                res[2] = right[i+k];
                max = total;
            }
        }

        return res;
    }
}

