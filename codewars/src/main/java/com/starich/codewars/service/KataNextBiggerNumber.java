package com.starich.codewars.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jason on 2017/6/3.
 */
public class KataNextBiggerNumber {

    public static long nextBiggerNumber(long n)
    {
        String number = String.valueOf(n);
        int fromIndex = -1;
        int toIndex = -1;
        for(int i = number.length() - 1; i > 0; i--){
            for(int j = i - 1; j >= 0; j--){
                if(number.charAt(i) > number.charAt(j)){
                    fromIndex = i;
                    toIndex = j;
                    break;
                }
                boolean match = false;
                if(i - j > 1){
                    for(int m = i - 1; m > j; m--){
                        if(number.charAt(m) > number.charAt(j)){
                            fromIndex = m;
                            toIndex = j;
                            match = true;
                            break;
                        }
                    }
                }
                if(match){
                    break;
                }
            }
            if(fromIndex != -1){
                break;
            }
        }
        if(fromIndex == -1){
            return -1;
        }
        String result = "";
        for(int k = 0; k < toIndex; k++){
            result += number.charAt(k);
        }
        List<Integer> toSort = new ArrayList<>();
        for(int m = toIndex; m < number.length(); m++){
            if(m != fromIndex){
                toSort.add(Integer.parseInt(String.valueOf(number.charAt(m))));
            }
        }
        result += number.charAt(fromIndex);
        Integer[] sort = (Integer[])toSort.toArray(new Integer[toSort.size()]);
        Arrays.sort(sort);
        for(int e = 0; e < sort.length; e++){
            result += sort[e];
        }
        return Long.parseLong(result);
    }

    public static void main(String[] args){
        System.out.println(nextBiggerNumber(2743));
    }

}
