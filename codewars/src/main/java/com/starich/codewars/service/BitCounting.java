package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/23.
 */
public class BitCounting {

    public static int countBits(int n){
        String binaryString = Integer.toBinaryString(n);
        int count = 0;
        for(int i = 0; i < binaryString.length(); i++){
            if("1".equals(binaryString.charAt(i))){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(countBits(1234));
    }
}