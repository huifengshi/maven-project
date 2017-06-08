package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class Dinglemouse {

    public static int howManyBees(final char [][] hive) {
        int count = 0;
        if(hive == null || hive.length == 0 || hive[0] == null || hive[0].length == 0){
            return count;
        }
        for(int i = 0; i < hive.length; i++){
            for(int j = 0; j < hive[i].length - 2; j++){
                if(hive[i][j] == 'b'){
                    if(hive[i][j + 1] == 'e' && hive[i][j + 2] == 'e'){
                        count++;
                    }
                }
            }
            for(int j = hive[i].length - 1; j > 1; j--){
                if(hive[i][j] == 'b'){
                    if(hive[i][j - 1] == 'e' && hive[i][j - 2] == 'e'){
                        count++;
                    }
                }
            }
        }
        for(int j = 0; j < hive[0].length; j++){
            for(int i = 0; i < hive.length - 2; i++){
                if(hive[i][j] == 'b' && hive[i + 1][j] == 'e' && hive[i + 2][j] == 'e'){
                    count++;
                }
            }
            for(int i = hive.length - 1; i > 1; i--){
                if(hive[i][j] == 'b' && hive[i - 1][j] == 'e' && hive[i - 2][j] == 'e'){
                    count++;
                }
            }

        }
        return count;
    }

    public static void main(String[] args){
        final char hive[][] = new char[][] {
                "bee.bee".toCharArray(),
                ".e..e..".toCharArray(),
                ".b..eeb".toCharArray()
        };
       System.out.println(howManyBees(hive));
    }

}
