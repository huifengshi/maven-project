package com.starich.core.service;

/**
 * Created by Jason on 2017/3/31.
 */
public class Person {
    public String name;
    private Integer age;

    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public void print(int i) {
        System.out.println("我在写数字： " + i);
    }

    public static void say(String str) {
        System.out.println("我在说： " + str);
    }
}
