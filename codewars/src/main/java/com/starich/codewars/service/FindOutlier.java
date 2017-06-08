package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class FindOutlier {

    static int find(int[] integers){
        boolean odd = false;
        if(Math.abs(integers[0]) % 2 + Math.abs(integers[1]) % 2 + Math.abs(integers[2]) % 2 <= 1){
            odd = false;
        }else{
            odd = true;
        }
        for(int i = 0; i < integers.length; i++){
            if(Math.abs(integers[i]) % 2 == 0 && odd){
                return integers[i];
            }
            if(Math.abs(integers[i]) % 2 == 1 && !odd){
                return integers[i];
            }
        }
        return 0;
    }

    public static void main(String[] args){
        System.out.println(find(new int[]{9,11,5,-44,1,3}));
    }
}
