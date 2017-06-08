package com.starich.component.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Jason on 2017/3/24.
 */
public class Java8Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Java8Test.class);

   public static void main(String[] args){
       //internal iterator
       List<Integer> values = Arrays.asList(1,2,3,4,5,6);
       /*values.forEach(new Consumer<Integer>() {
           @Override
           public void accept(Integer integer) {
               LOGGER.info(integer.toString());
           }
       });*/

       //values.forEach(value -> System.out.println(value));
       values.forEach(System.out::println);
   }
}
