package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class DigPow {
    public static long digPow(int n, int p) {
        String numbers = Integer.toString(n);
        long sum = 0L;
        for(int i = 0; i < numbers.length(); i++){
            sum += Math.pow(Integer.parseInt(String.valueOf(numbers.charAt(i))),(p + i));
        }
        if((sum % p) == 0){
            return sum/p;
        }else{
            return -1;
        }
    }
}
