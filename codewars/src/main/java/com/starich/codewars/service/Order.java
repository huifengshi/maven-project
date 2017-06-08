package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class Order {
    public static String order(String words) {
        if(words == null || words.length() == 0){
            return words;
        }
        String[] word = words.split(" ");
        String[] result = new String[word.length];
        for(int i = 0; i < word.length; i++){
            result[Integer.parseInt(word[i].replaceAll("[^1-9]",  "")) - 1] = word[i];
        }
        String order = "";
        for(int i = 0; i < result.length; i++){
            order += result[i];
            if(i < result.length - 1){
                order += " ";
            }
        }
        return order;
    }

    public static void main(String[] args){
        System.out.println("Fo1r the2 g3ood 4of th5e pe6ople".equals(order("4of Fo1r pe6ople g3ood th5e the2")));
    }


}
