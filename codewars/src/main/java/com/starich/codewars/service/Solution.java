package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class Solution {

    public int solution(int number) {
        int sum = 0;
        for(int i = 1; i * 3 < number; i++){
            sum += i *3;
        }
        for(int j = 1; j * 5 < number; j++){
            sum += j * 5;
        }
        for(int k = 1; k * 15 < number; k++){
            sum -= k * 15;
        }
        return sum;
    }

}
