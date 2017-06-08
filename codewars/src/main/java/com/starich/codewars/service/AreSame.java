package com.starich.codewars.service;

import java.util.Stack;

/**
 * Created by Jason on 2017/5/24.
 */
public class AreSame {
    public static boolean comp(int[] a, int[] b) {
        if(a == null || b == null){
            return false;
        }else if(a.length == 0 && b.length == 0){
            return true;
        }else if(a.length != b.length){
            return false;
        }else{
            int result = 0;
            for(int i = 0; i < a.length; i++){
                result = result ^(a[i] * a[i]) ^ b[i];
            }
            if(result == 0){
                return true;
            }else{
                return false;
            }
        }
    }

    public static void main(String[] args){
        int[] a = new int[]{11,2};
        int[] b = new int[]{4,121};
        System.out.println(comp(a, b));
    }
}
