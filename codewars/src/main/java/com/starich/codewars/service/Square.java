package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/1.
 */
public class Square {
    public static boolean isSquare(int n) {
        if(n < 0){
            return false;
        }
        for(int i = 0; i <= n; i++){
            int k = i * i;
            if(k < n){
                continue;
            }else if(k == n){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }


}