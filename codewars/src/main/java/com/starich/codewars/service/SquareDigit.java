package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/23.
 */
public class SquareDigit {

    public static int squareDigits(int n) {
        String s = String.valueOf(n);
        String result = "";
        for(int i = 0; i < s.length(); i++){
             result += Integer.parseInt(String.valueOf(s.charAt(i))) * Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        return Integer.parseInt(result);
    }

    public static void main(String[] args){
        System.out.println(squareDigits(9119));
    }
}
