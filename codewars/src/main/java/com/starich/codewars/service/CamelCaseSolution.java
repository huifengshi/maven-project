package com.starich.codewars.service;

/**
 * Created by Jason on 2017/6/2.
 */
public class CamelCaseSolution {
    static String toCamelCase(String s){
        StringBuilder sb = new StringBuilder();
        String[] sarray = s.split("[-_]");
        sb.append(sarray[0]);
        for(int i = 1; i < sarray.length; i++){
            if(sarray[i].length() > 1){
                sb.append(String.valueOf(sarray[i].charAt(0)).toUpperCase()).append(sarray[i].substring(1, sarray[i].length()));
            }else{
                sb.append(String.valueOf(sarray[i].charAt(0)).toUpperCase());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(toCamelCase("the_Stealth_Warrior"));
    }
}
