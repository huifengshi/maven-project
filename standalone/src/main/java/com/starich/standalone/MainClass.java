package com.starich.standalone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2017/5/4.
 */
public class MainClass {

    private static final int MILLION = 10000000;

    public static void main(String[] args){
        List<User> l  = new ArrayList<>();
        for(int i = 0; i < MILLION; i++){
            try{
                Thread.sleep(1);
            }catch (InterruptedException ex){

            }
            l.add(new User());
        }
    }

    static class User{
        private Integer id;
        private String userName;
        private String password;

    }
}
