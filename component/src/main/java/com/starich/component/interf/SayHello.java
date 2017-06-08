package com.starich.component.interf;

/**
 * Created by Jason on 2017/3/10.
 */
public interface SayHello {

    String say(String name);
    String yell(String name);
    void loopSay(String name, int milliSecondsStep, int times);

}
