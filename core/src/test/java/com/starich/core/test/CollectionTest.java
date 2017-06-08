package com.starich.core.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2017/3/29.
 */
public class CollectionTest {

    private static final  Logger LOGGER = LoggerFactory.getLogger(CollectionTest.class);

    @Test
    public void testSubList(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        List<String> subList = list.subList(0, 3);
        for(String s : subList){
            LOGGER.info("s:" + s);
        }
        list.add("11");
        subList.remove("2");
        LOGGER.info("sublist size:" + subList.size());
        LOGGER.info("list size:" + list.size());
    }
}
