package com.algorithm.array;

import java.util.HashSet;
import java.util.*;

/**
 * Given an array prices[] of non-negative integers, representing the prices of the stocks on different days, return the maximum profit possible by buying and selling the stocks on different days when at most one transaction is allowed. Here one transaction means 1 buy + 1 Sell. If it is not possible to make a profit then return 0.
 *
 * Note: Stock must be bought before being sold.
 */
public class StockBuySell {
    public static void main(String[] args) {
        int[] prices = {7, 10, 1, 3, 6, 9, 2};
        System.out.println(maxProfit(prices));
    }

    /**
     * left, then try to find next big int
     * @param prices
     * @return
     */
    private static boolean maxProfit(int[] prices) {
        Map<Integer,String> profit= new HashMap();
        int max=0;
        for(int i=0; i<prices.length-1;i++){
            max=0;
            profit.put(i, prices[i] + " -> 0" );
            if(i==prices.length-1){
                break;
            }
            for(int j=i+1; j<prices.length;j++){
                if(prices[j]>prices[i]){
                    int diff = prices[j]- prices[i];
                    if(diff>max) {
                        max=Math.max(diff, max);
                        profit.put(i, prices[i] + " -> " + prices[j]);
                    }
                }
            }
        }
        System.out.println("buy-sell: "+profit);
        return false;
    }
}
