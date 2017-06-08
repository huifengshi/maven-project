package com.starich.codewars.service;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Jason on 2017/4/26.
 */
public class NthSeries {

    public static String seriesSum(int n) {
        if(n == 0){
            return "0.00";
        }else{
            BigDecimal dividend = new BigDecimal("1");
            BigDecimal total = new BigDecimal("0");
            for(int i = 1; i <= n; i++){
                BigDecimal divisor = new BigDecimal(String.valueOf(i * 3  - 2));
                total = total.add(dividend.divide(divisor, MathContext.DECIMAL128));
            }
            return total.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
    }
}
