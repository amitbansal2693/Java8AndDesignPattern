package com.algorithm.practice.array.Oct10;

import com.algorithm.practice.array.pair_triplet.*;

import java.util.*;

public class Day10_oct implements CombinationSumInterface, PairTripletInterface {

    public static void main(String[] args) {
        Day10_oct solver=new Day10_oct();
        // Test 1: hasPair
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("Test hasPair: " + solver.hasPair(arr1, 7));

        // Test 2: findAllPairs
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Test findAllPairs: " + solver.findAllPairs(arr2, 7));

        // Test 2: findAllPairs
        //int[] arrr2 = {1,1,2, 2, 3, 4, 5,5, 6, 7};
        //System.out.println("Test findAllPairs: " + solver.findAllPairs_withduplicatesInput(arrr2, 7));

        // Test 3: countPairs
        int[] arr3 = {1, 2,2,2, 3, 4, 5, 2, 3,3};
        System.out.println("Test countPairs:  " + solver.countPairs(arr3, 7));

        // Test 4: findAllTriplets
        int[] arr4 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test findAllTriplets: " + solver.findAllTriplets(arr4, 0));

        // Test 5: countTriplets
        int[] arr5 = {1, 2, 3, 4, 5};
        System.out.println("Test countTriplets: " + solver.countTriplets(arr5, 9));

        // Test 6: combinationSum
        int[] candidates1 = {2, 3, 6, 7};
        System.out.println("Test combinationSum([2,3,6,7], 7): "
                + solver.combinationSum(candidates1, 7));

        // Test 7: combinationSumII
        int[] candidates2 = {10, 1, 2, 7, 6, 1, 5};
        System.out.println("Test combinationSumII([10,1,2,7,6,1,5], 8): "
                + solver.combinationSumII(candidates2, 8));

        // Test 8: generateCombinations
        System.out.println("Test generateCombinations(4, 2): " + solver.generateCombinations(4, 2));
    }

    public List<List<Integer>> fourSum(int[] arr, int target) {
        List<List<Integer>> results=new ArrayList();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = i+1; j <arr.length-2 ; j++) {
                int left = j+1;
                int right = arr.length-1;
                while (left < right){
                    int sum= arr[i]+arr[j]+arr[left]+arr[right];
                    if(sum<target){
                        left++;
                    } else if (sum> target) {
                        right--;
                    } else {
                        results.add(List.of(arr[i],arr[j],arr[left],arr[right]));
                        left++;
                        right--;
                    }
                }
            }
        }
        return results;
    }

    public List<List<? extends Number>> fourSum_NoSorting(int[] arr, int target) {
        List<List<? extends Number>> results=new ArrayList();
        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = i+1; j <arr.length-2 ; j++) {
                Set<Long> seen =new HashSet<>();
                for (int k = j+1; k <arr.length ; k++) {
                    Long compliment = (long) (target - (arr[i]+ arr[j]+arr[k]));
                    if (seen.contains(compliment)) {
                        Long sum = arr[i]+ arr[j] + arr[k] +compliment;
                        if((target-sum)==0)
                        results.add(List.of(arr[i],arr[j], arr[k],compliment).stream().sorted().toList());
                    } else seen.add((long) arr[k]);

                }
            }
        }
        return results.stream().distinct().toList();
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {


        return head;
    }
    public int removeDuplicates(int[] nums) {
        return (int) Arrays.stream(nums).distinct().count();
    }
    public int removeDuplicates2(int[] nums) {

        int k = 1;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
    public int removeElement(int[] nums, int val) {
      return Arrays.stream(nums).boxed().filter(e->e!=val).toList().size();
    }

    public List<List<Integer>> fourSum_NoSorting(int[] arr, long target) {

        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < arr.length - 3; i++) {

            for (int j = i + 1; j < arr.length - 2; j++) {

                Set<Long> seen = new HashSet<>();

                for (int k = j + 1; k < arr.length; k++) {

                    long complement =
                            target - arr[i] - arr[j] - arr[k];

                    if (complement >= Integer.MIN_VALUE &&
                            complement <= Integer.MAX_VALUE &&
                            seen.contains(complement)) {
                        results.add(List.of(arr[i],arr[j], arr[k],(int) complement).stream().sorted().toList());
                         }

                    seen.add((long) arr[k]);
                }
            }
        }

        return results.stream().distinct().toList();
    }
    @Override
    public boolean hasPair(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return true;
            } else if (sum < target)
                left++;
            else
                right--;
        }

        return false;
    }

    @Override
    public List<List<Integer>> findAllPairs(int[] arr, int target) {
        List<List<Integer>> results =new ArrayList<>();
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;
        while (left < right){
            int sum =arr[left]+arr[right];
            if (sum == target) {
                results.add(List.of(arr[left++],arr[right--]));
            } else if (sum < target) {
                left++;
            } else right--;

        }
        return results;
    }

    @Override
    public int countPairs(int[] arr, int target) {
        System.out.println("Input "+Arrays.stream(arr).boxed().toList() + " Target: "+target);
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] +arr[right];
            if(sum==target) results.add(List.of(arr[left++],arr[right--]));
            else if(sum < target) left++;
            else right--;
        }
        System.out.println("found: "+results);
        return results.size();
    }

    @Override
    public List<List<Integer>> findAllTriplets(int[] arr, int target) {
        System.out.println("Input " + Arrays.stream(arr).boxed().toList() + " Target: " + target);
        Arrays.sort(arr);
        List<List<Integer>> results = new ArrayList<>();
        //fix 1st index
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    results.add(List.of(arr[i], arr[left++], arr[right--]));
                } else if (sum < target) {
                    left++;
                } else right--;
            }
        }
        System.out.println("found: " + results);
        return results;
    }

    @Override
    public int countTriplets(int[] arr, int target) {
        System.out.println("Input " + Arrays.stream(arr).boxed().toList() + " Target: " + target);
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    count++;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else right--;
            }
        }
        return count;
    }

    @Override
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        System.out.println("combinationSum Input " + Arrays.stream(candidates).boxed().toList() + " Target: " + target);
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        findCombinationBacktracking(candidates, 0, target, new ArrayList<Integer>(), results);
        System.out.println("Results: combinationSum "+results);
        return results;
    }

    private void findCombinationBacktracking(int[] arr, int start, int target,
                                             ArrayList<Integer> current, List<List<Integer>> results) {
        //1. target achieved
        if (target == 0) {
            System.out.println("Adding pair in result "+current);
            results.add(new ArrayList<>(current));
            return;
        }
        //2. overflow
        if (start >= arr.length) {
            return;
        }
        //3. for loop from start index
        for (int i = start; i < arr.length; i++) {
            if(arr[i]>target){
                break;
            }
            current.add(arr[i]);
            //update target
            findCombinationBacktracking(arr,i,target-arr[i],current,results);
            current.remove(current.size()-1);
        }

    }

    @Override
    public List<List<Integer>> combinationSumII(int[] arr, int target) {
        List<List<Integer>> results= new ArrayList<>();
        Arrays.sort(arr);
        findUniqueCombinationBacktracking(arr, 0, target, new ArrayList<Integer>(), results);
        System.out.println("combinationSumII "+results);
        return results;
    }

    private void findUniqueCombinationBacktracking(int[] arr, int start, int target,
                                                   ArrayList<Integer> current, List<List<Integer>> results) {
        if (target == 0) {
            System.out.println("Print combo:"+current);
            results.add(new ArrayList<>(current));
            return;
        }
        if (start >= arr.length) {
            return;
        }
        for (int i = start; i < arr.length; i++) {
            if (i > start && arr[i - 1] == arr[i]) {
                continue;
            }
            if (arr[i] > target) {
                break;
            }
            current.add(arr[i]);
            findUniqueCombinationBacktracking(arr, i + 1, target - arr[i], current, results);
            current.remove(current.size() - 1);
        }
    }

    @Override
    public List<List<Integer>> generateCombinations(int n, int k) {
        return List.of();
    }

    @Override
    public List<List<Integer>> combinationSum_NoDuplicates(int[] candidates, int target) {
        return List.of();
    }
}
