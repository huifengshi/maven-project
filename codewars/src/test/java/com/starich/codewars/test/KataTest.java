package com.starich.codewars.test;

import com.starich.codewars.service.Kata;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jason on 2017/4/26.
 */
public class KataTest {
    @Test
    public void tests() {
        assertEquals("(123) 456-7890", Kata.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));
    }

    @Test
    public void test(){
        System.out.println(1/2);
    }
}
