package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/23.
 */
public class SpinWords {

    public static  String spinWords(String sentence) {
        String[] words = sentence.split(" ");
        String result = "";
        for(int i = 0; i < words.length; i++){
            if(words[i].length() < 5){
                result = result + words[i] + " ";
            }else{
                for(int j = words[i].length() - 1; j >= 0; j--){
                    result = result + String.valueOf(words[i].charAt(j));
                }
                if(i < words.length - 1) {
                    result += " ";
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(spinWords("This is a [tset ]"));
    }
}
