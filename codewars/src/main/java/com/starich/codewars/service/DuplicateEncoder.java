package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class DuplicateEncoder {
    static String encode(String word){
        if(word == null || word.length() == 0) {
            return word;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++){
            int cnt = 0;
            boolean moreThanOnce = false;
            for(int j = 0; j < word.length(); j++){
                if(i != j && String.valueOf(word.charAt(i)).equalsIgnoreCase(String.valueOf(word.charAt(j)))){
                    moreThanOnce = true;
                    break;
                }
            }
            if(moreThanOnce){
                sb.append(")");
            }else{
                sb.append("(");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(")()())()(()()(".equals(encode("Prespecialized")));
        System.out.println("))))())))".equals(encode("   ()(   ")));
    }
}
