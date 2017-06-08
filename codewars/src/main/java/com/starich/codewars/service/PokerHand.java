package com.starich.codewars.service;

/**
 * Created by Jason on 2017/6/4.
 */
public class PokerHand {

    public enum Result { TIE, WIN, LOSS }
    public enum Card{2, 3}
    PokerHand(String hand)
    {
    }

    public Result compareWith(PokerHand hand) {
        return Result.TIE;
    }

}
