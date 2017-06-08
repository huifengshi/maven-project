package com.starich.codewars.service;

/**
 * Created by Jason on 2017/5/26.
 */
public class Dubstep {
    public static String SongDecoder(String song) {
        return song.replaceAll("^(WUB)+", "").replaceAll("(WUB)+$", "").replaceAll("(WUB)+", " ");
    }

    public static void main(String[] args) {
        System.out.println(SongDecoder("RWUBWUBWUBLWUB"));
    }

}
