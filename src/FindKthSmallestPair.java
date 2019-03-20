import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindKthSmallestPair {

    public int smallestDistancePair(int[] nums, int k) {
        return binarySearch(nums, k);
    }

    // Binary search
    private int binarySearch(int[] nums, int k) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];

        while(lo <= hi) {
            int mi = lo + (hi - lo) / 2;

            // Calculate how many pairs distance less than mi
            int j = 1;
            int pairs = 0;

            for(int i = 0; i < nums.length - 1; i++) {
                while(j < nums.length && nums[j] - nums[i] <= mi) j++;
                pairs += j - i - 1;
            }

            if(pairs >= k) {
                hi = mi - 1;
            }
            else {
                lo = mi + 1;
            }
        }

        return lo;
    }

    // Simple and naive brute force solution
    private int bruteForce(int[] nums, int k) {
        int max = -1;

        for(int num : nums) {
            max = Math.max(num, max);
        }

        // Since there isn't negative value in nums, the max distance
        // should less than max value;
        int[] bucket = new int[++max];

        // Load distance
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                int distance = Math.abs(nums[i] - nums[j]);
                ++bucket[distance];
            }
        }

        // Loop bucket array to get Kth distance
        int result = 0;
        while(k > 0) {
            k -= bucket[result++];
        }

        return result;
    }

    // Using heap data structure
    private int heapSorting(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<Side> queue = new PriorityQueue<>(Comparator.comparing(Side::length));

        // The heap will guarantee always contains minimum distances
        for(int i = 0; i < nums.length - 1; i++) {
            Side side = new Side(i, i+1, nums);
            queue.offer(side);
        }

        int result = -1;
        while (k-- > 0) {
            Side side = queue.poll();
            result = side.length;

            if(side.next < nums.length) {
                Side nextSide = new Side(side.root, ++side.next, nums);
                queue.offer(nextSide);
            }
        }

        return result;
    }

    class Side {
        int root;
        int next;
        int length;

        public Side(int root, int next, int[] nums) {
            this.root = root;
            this.next = next;
            this.length = nums[next] - nums[root];
        }

        public int length() {
            return length;
        }
    }
}
