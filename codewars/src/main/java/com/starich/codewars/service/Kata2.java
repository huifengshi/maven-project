package com.starich.codewars.service;

/**
 * Created by Jason on 2017/6/1.
 */
public class Kata2 {

    public static int[] sortArray(int[] array) {
        if(array == null || array.length == 0){
            return array;
        }
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length - 1; j++){
                if(array[j] % 2 == 0){
                    continue;
                }else{
                    for( int k = j + 1; k < array.length; k++){
                        int tmp;
                        if(array[k] % 2 == 1){
                            if(array[k] < array[j]){
                                tmp = array[j];
                                array[j] = array[k];
                                array[k] = tmp;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return array;
    }

    public static void main(String[] args){
       /* int[] array = new int[]{2,4,6,15,67,32,23,0,5};*/
        int[] array = new int[]{5, 3, 2, 8, 1, 4};
        int[] sorted = sortArray(array);
        for(int i = 0; i < sorted.length; i++){
            System.out.print(sorted[i]);
            System.out.print(" ");
        }
    }

}
