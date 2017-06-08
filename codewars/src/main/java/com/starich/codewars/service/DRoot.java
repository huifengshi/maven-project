package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class DRoot {

    public static int digital_root(int n) {
       String number = Integer.toString(n);
       if(number.length() == 1){
           return n;
       }else{
           int sum = 0;
           for(int i = 0; i < number.length(); i++){
                sum += Integer.parseInt(String.valueOf(number.charAt(i)));
           }
           return digital_root(sum);
       }
    }

    public static void main(String[] args){
        System.out.println(digital_root(16));
    }

}
