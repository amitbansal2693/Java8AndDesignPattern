package com.algorithm.practice.array.pair_triplet;

import java.util.List;
public interface CombinationSumInterface {

    List<List<Integer>> combinationSum(int[] candidates, int target);

    List<List<Integer>> combinationSum_NoDuplicates(int[] candidates, int target);
}