package com.starich.codewars.test;

import com.starich.codewars.service.NthSeries;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Jason on 2017/4/26.
 */
public class NthSeriesTest {

    @Test
    public void test1() {
        assertEquals(NthSeries.seriesSum(5), "1.57");
    }
    @Test
    public void test2() {
        assertEquals(NthSeries.seriesSum(9), "1.77");
    }
    @Test
    public void test3() {
        assertEquals(NthSeries.seriesSum(15), "1.94");
    }
}
