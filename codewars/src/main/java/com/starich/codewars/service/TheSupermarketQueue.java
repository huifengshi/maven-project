package com.starich.codewars.service;

/**
 * Created by Jason on 2017/6/1.
 */
public class TheSupermarketQueue {

    public static int solveSuperMarketQueue(int[] customers, int n) {
        int[] time = new int[n];
        for(int i = 0; i < customers.length; i++){
            int mini = 0;
            for(int j = 1; j < n; j++){
                if(time[j] < time[mini]){
                    mini = j;
                }
            }
            time[mini] += customers[i];
        }
        int max = 0;
        for(int k = 1; k < n; k++){
            if(time[k] > time[max]){
                max = k;
            }
        }
        return time[max];
    }
}
