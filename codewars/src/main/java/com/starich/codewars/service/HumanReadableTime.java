package com.starich.codewars.service;

/**
 * Created by Jason on 2017/6/1.
 */
public class HumanReadableTime {

    public static String makeReadable(int seconds) {
        int hour = seconds / 3600;
        int minute = (seconds - hour * 3600) / 60;
        int second = seconds - 3600 * hour - 60 * minute;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static void main(String[] args){
        System.out.println(makeReadable(61));
    }

}
