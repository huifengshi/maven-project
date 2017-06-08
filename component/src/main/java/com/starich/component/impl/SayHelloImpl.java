package com.starich.component.impl;

import com.starich.component.interf.SayHello;
import org.springframework.stereotype.Service;

/**
 * Created by Jason on 2017/3/11.
 */
@Service
public class SayHelloImpl implements SayHello {

    public String say(String name) {
        StringBuilder sb = new StringBuilder();
        return sb.append("Hello World").append(",").append("this is ").append(name).toString();
    }

    public  String yell(String name){
        return "no," + name;
    }

    public void loopSay(String name, int milliSecondsStep, int times) {
        for(int i = 0; i < times; i++){
            System.out.println(say(name));
            try{
                Thread.sleep(milliSecondsStep);
            }catch (InterruptedException ie){

            }
        }
    }
}
