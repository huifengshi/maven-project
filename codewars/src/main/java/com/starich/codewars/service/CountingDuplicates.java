package com.starich.codewars.service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jason on 2017/6/1.
 */
public class CountingDuplicates {

    public static int duplicateCount(String text) {
        text = text.toLowerCase();
        Set<String> duplicate = new HashSet<>();
        for(int i = 0; i < text.length(); i++){
            if(text.indexOf(text.charAt(i)) != text.lastIndexOf(text.charAt(i))){
                duplicate.add(String.valueOf(text.charAt(i)));
            }
        }
        return duplicate.size();
    }

}
