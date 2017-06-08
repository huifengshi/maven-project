package com.starich.codewars.service;

import java.util.Arrays;

/**
 * Created by Jason on 2017/5/23.
 */
public class FindOdd {

    public static int findIt(int[] A) {
        int count = 0;
        Integer integer = null;
        int[] another = new int[A.length];
        int j = 0;
        for(int i = 0; i < A.length; i++){
            if(integer == null){
                integer = A[i];
                count++;
            }else if(integer == A[i]){
                count++;
            }else{
                another[j] = A[i];
                j++;
            }
        }
        if(count % 2 == 1){
            return integer;
        }else {
            return findIt(Arrays.copyOf(another, j));
        }
    }

    public static void main(String[] args){
        System.out.print(FindOdd.findIt(new int[]{20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5}));
    }
}
