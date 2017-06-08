package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class Persist {
    public static int persistence(long n) {
        return persistence(n, 0);
    }

    public static int persistence(long n , int times){
        String number = Long.toString(n);
        if(number.length() == 1){
            return times;
        }else{
            long sum = 1;
            for(int i = 0; i < number.length(); i++){
                sum *= Integer.parseInt(String.valueOf(number.charAt(i)));
            }
            times++;
            return persistence(sum, times);
        }
    }

    public static void main(String[] args){
        System.out.println(persistence(39));
    }
}
