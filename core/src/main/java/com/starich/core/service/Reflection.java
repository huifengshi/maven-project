package com.starich.core.service;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Jason on 2017/3/30.
 */
public class Reflection {


    public static void main(String[] args){
       /* //打印Class类
        Reflection t = new Reflection();
        System.out.println(t.getClass());
        System.out.println(t.getClass().getName());

        //Class类实例化
        //1.getClass();
        Class<? extends Reflection> c1 = t.getClass();
        System.out.println(c1);

        //2. .class
        Class<String> c2 = String.class;
        System.out.println(c2);

        //3. forName
        Class<?> c3 = null;
        try{
          c3 = Class.forName("Reflection");
        }catch (ClassNotFoundException e){
        }
        System.out.println(c3);

        createInstanceFromClass();

        createInstanceFromClassWithConstructor();*/
        /*getConstructors();*/
        /*getClassImplementedInterfaces();*/
        /*getSuperClass();*/
       /* getAllMethods();*/
        /*getAllFields();*/
        getFieldValue();
    }

    private static void createInstanceFromClass(){
        Class<?> c = null;
        try{
            c = Class.forName("java.lang.String");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        String s = null;
        try{
            s = (String)c.newInstance();
        }catch (InstantiationException e){

        }catch (IllegalAccessException e){

        }
        System.out.println("s length:" + s.length());
    }

    private static void createInstanceFromClassWithConstructor(){
        Class<?> c = null;
        try{
            c = Class.forName("java.lang.String");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        char[] ch = {'h','e','l','l','o'};
        String s = null;
        try {
            Constructor<?> con = c.getConstructor(char[].class);
            s = (String)con.newInstance(ch);
            System.out.println("s:" + s);
        }catch (NoSuchMethodException e){

        }catch (IllegalAccessException e){

        }catch (InvocationTargetException e){

        }catch (InstantiationException e){

        }
    }

    private static void getConstructors(){
        Class<?> c = null;
        try{
            c = Class.forName("java.lang.Boolean");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        Constructor<?>[] cons = c.getConstructors();
        System.out.println(Arrays.toString(cons));
    }

    private static void getClassImplementedInterfaces(){
        Class<?> c = null;
        try{
            c = Class.forName("java.lang.Boolean");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        Class<?>[] in = c.getInterfaces();
        System.out.println(Arrays.toString(in));
    }

    private static void getSuperClass(){
        Class<?> c = null;
        try{
            c = Class.forName("java.lang.Boolean");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        Class<?> su = c.getSuperclass();
        System.out.print(su);
    }

    private static void getAllMethods(){
        Class<?> c = null;
        try{
            c = Class.forName("java.lang.Boolean");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        Method[] m = c.getMethods();
        for(Method m1 : m){
            System.out.println(m1);
        }
    }

    private static void getAllFields(){
        Class<?> c = null;
        try{
            c = Class.forName("java.lang.Boolean");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        Field[] fields = c.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field);
        }
    }

    private static void getFieldValue(){
        Person p = new Person("zhangsan", 32);
        Class<?> c = p.getClass();
        try {
            Field f1 = c.getField("name");
            String name = (String)f1.get(p);
            System.out.println("姓名: " + name);

            Field f2 = c.getDeclaredField("age");
            f2.setAccessible(true);
            int age = (int)f2.get(p);
            System.out.println("年龄:" + age);

        }catch (NoSuchFieldException ex){
            ex.printStackTrace();
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        }
    }



    @Test
    public void testFieldChange(){
        Person p = new Person("王二狗", 32);
        System.out.println(p.name);

        Class<?> c = p.getClass();
        try {
            Field f = c.getDeclaredField("name");
            f.set(p, "张二蛋");
            System.out.println(p.name);
        }catch (NoSuchFieldException ex){
            ex.printStackTrace();
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void testMethodCall(){
        Person p = new Person("zhangsan", 32);
        Class<?> c = p.getClass();

        try {
            Method m1 = c.getMethod("print", int.class);
            m1.invoke(p, 10);

            Method m2 = c.getMethod("say", String.class);
            m2.invoke(null, "你妹");
        }catch (NoSuchMethodException ex){
            ex.printStackTrace();
        }catch (InvocationTargetException ex){
            ex.printStackTrace();
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        }
    }

}
