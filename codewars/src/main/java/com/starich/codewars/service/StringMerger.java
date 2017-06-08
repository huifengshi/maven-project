package com.starich.codewars.service;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Jason on 2017/5/24.
 */
public class StringMerger {

    /*
    * 'codewars' is a merge from 'cdw' and 'oears':

            s:  c o d e w a r s   = codewars
        part1:  c   d   w         = cdw
        part2:    o   e   a r s   = oears
    * */
    public static boolean isMerge(String s, String part1, String part2) {
        if(s.length() != part1.length() + part2.length()){
            return false;
        }
        int j = 0;
        int k = 0;
        Queue<Integer> sameJ = new LinkedList<>();
        Queue<Integer> samek = new LinkedList<>();
        Queue<Integer> samei = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            boolean matchPart1 = false;
            boolean matchPart2 = false;
            if(j < part1.length() && s.charAt(i) == part1.charAt(j)){
                matchPart1 = true;
            }
            if(k < part2.length() && s.charAt(i) == part2.charAt(k)){
                matchPart2 = true;
            }

            if(matchPart1 && matchPart2){
                samei.offer(i);
                sameJ.offer(j);
                samek.offer(k);
                j++;
                continue;
            }else if(matchPart1){
                j++;
                continue;
            }else if(matchPart2){
                k++;
                continue;
            }

            if(!matchPart1 && !matchPart2 && samei.size() > 0){
                i = samei.poll();
                j = sameJ.poll();
                k = samek.poll() + 1;
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    public  static void main(String[] args){
        //System.out.println(isMerge("Can we merge it? No, we can't!", "Cwemre ? Ys,w !", "an  egite ecan"));
        /*System.out.println(isMerge("Can we merge it? Yes, we can!", "anwe mee it? Ye can!", "C rgs, we"));*/
       /* System.out.println(isMerge("codewars", "codewars", "s"));*/
       /* System.out.println(isMerge("GoYs_==`;]Lo0EX^pxC?9BGn8t_JA<`7ky:G-GoYs_==`;]Lo0EX^pxC?9BGnE\"6\\eGq)BEj[..%tP;_yl=(.l8b/hHx39Wf",
                "GoYs_==`;]Lo0EX^pxC?9BGnE\"6\\eGq)BEj[..%t",
                "GoYs_==`;]Lo0EX^pxC?9BGn8t_JA<`7ky:G-P;_yl=(.l8b/hHx39Wf"));*/
        System.out.println(isMerge("Bananas from Bahamas", "Bahas", "Bananas from am"));
    }
}
