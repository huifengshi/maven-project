package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class LongestConsec {
    public static String longestConsec(String[] strarr, int k) {
       if(strarr == null || strarr.length == 0 || k > strarr.length || k <= 0){
           return "";
       }
       String[] comStrArr = new String[strarr.length - k + 1];
       for(int i = 0; i <= strarr.length - k; i++){
           String tmp = "";
           for(int j = i; j < i + k; j++){
                tmp += strarr[j];
           }
           comStrArr[i] = tmp;
       }

       for(int i = 0; i < comStrArr.length - 1; i++){
           for(int j = 0; j < comStrArr.length - i - 1; j++){
               String tmp;
               if(comStrArr[j].length() < comStrArr[j + 1].length()){
                   tmp = comStrArr[j];
                   comStrArr[j] = comStrArr[j + 1];
                   comStrArr[j + 1] = tmp;
               }
           }
       }
       return comStrArr[0];

    }

    public static void main(String[] args){
        System.out.println(longestConsec(new String[]{"zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"}, 2));
        System.out.println(longestConsec(new String[] {"itvayloxrp","wkppqsztdkmvcuwvereiupccauycnjutlv","vweqilsfytihvrzlaodfixoyxvyuyvgpck"}, 2));
    }

}
