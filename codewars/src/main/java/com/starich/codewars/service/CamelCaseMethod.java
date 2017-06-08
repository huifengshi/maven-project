package com.starich.codewars.service;

/**
 * Created by Jason on 2017/6/1.
 */
public class CamelCaseMethod {

    public static String camelCase(final String string) {
        String[] split = string.split(" ");
        String result = "";
        for(int i = 0; i < split.length; i++){
            if(!"".equals(split[i])) {
                result += String.valueOf(split[i].charAt(0)).toUpperCase() + split[i].substring(1, split[i].length());
            }
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(camelCase("test case"));
    }
}
