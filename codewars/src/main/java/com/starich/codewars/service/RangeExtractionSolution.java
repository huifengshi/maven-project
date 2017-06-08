package com.starich.codewars.service;

/**
 * Created by Jason on 2017/6/3.
 */
public class RangeExtractionSolution {

    public static String rangeExtraction(int[] arr) {

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j] > arr[j + 1]){
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < arr.length;){
            for(int m = k; m < arr.length; m++){
                if((m + 1) < arr.length && arr[m + 1] - arr[m] == 1){
                    continue;
                }else{
                    if(m - k >= 2){
                        sb.append(arr[k]).append("-").append(arr[m]).append(",");
                        k = m + 1;
                        break;
                    }else{
                        sb.append(arr[k]).append(",");
                        k++;
                        break;
                    }
                }
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void  main(String[] args){
        int[] arr = new int[]{-3,-2,-1,2,10,15,16,18,19,20};
        System.out.println(rangeExtraction(arr));
    }
}
