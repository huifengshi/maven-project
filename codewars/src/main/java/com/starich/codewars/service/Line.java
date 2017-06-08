package com.starich.codewars.service;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Jason on 2017/5/31.
 */
public class Line {

    public static String Tickets(int[] peopleInLine)
    {
        Queue<Integer> twentyFive = new LinkedList<>();
        Queue<Integer> fifty = new LinkedList<>();
        Queue<Integer> hundred = new LinkedList<>();
        for(int i = 0; i < peopleInLine.length; i++){
            switch (peopleInLine[i]){
                case 25: twentyFive.offer(25);break;
                case 50: if(twentyFive.poll() == null){
                         return "NO";
                }else{
                         fifty.offer(50);
                }break;
                case 100: if(fifty.size() > 0 && twentyFive.size() > 0){
                         fifty.poll();
                         twentyFive.poll();
                         hundred.offer(100);
                }else if(twentyFive.size() >= 3){
                    twentyFive.poll();
                    twentyFive.poll();
                    twentyFive.poll();
                    hundred.offer(100);
                }else{
                    return "NO";
                }break;
                default:return "NO";
            }
        }
        return "YES";
    }
    
}
