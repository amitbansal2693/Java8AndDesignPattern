package com.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
    }

    /**
     * returns unique pairs with sum==0
     *
     * @param nums
     * @return
     */
    static List<List<Integer>> threeSum(int[] nums) {
int distance= Integer.MAX_VALUE;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //hold first, adn then find remainign 2 using pointer.
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                    ));

                    // Skip duplicate left/right values
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum < 0) {

                    // Need a bigger sum
                    left++;

                } else {

                    // Need a smaller sum
                    right--;
                }
            }
        }

        return result;
    }
}
