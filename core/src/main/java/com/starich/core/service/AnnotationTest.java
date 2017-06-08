package com.starich.core.service;

import com.starich.core.annotation.Autowired;
import com.starich.core.annotation.MethodTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Jason on 2017/4/25.
 */
public class AnnotationTest {

    @Autowired
    private int i;

    @MethodTest(id=1, description = "hello method1")
    public void method1(){
    }

    @MethodTest(id=2)
    public void method2(){

    }

    @MethodTest(id=3, description = "last method3")
    public void method3(){
    }

    public static void main(String[] args){
        Method[]  methods = AnnotationTest.class.getDeclaredMethods();
        Field[] fields = AnnotationTest.class.getDeclaredFields();
        for(Method method : methods){
            boolean hasAnnotation = method.isAnnotationPresent(MethodTest.class);
            if(hasAnnotation){
                MethodTest annotation = method.getAnnotation(MethodTest.class);
                System.out.println(String.format("Test(method=%s,id=%s,description=%s)", method.getName(), annotation.id(), annotation.description()));
            }
        }

        for(Field field : fields){
            boolean hasAnnotation = field.isAnnotationPresent(Autowired.class);
            if(hasAnnotation){
                System.out.println(String.format("Autowired(field=%s)", field.getName()));
            }
        }
    }
}
