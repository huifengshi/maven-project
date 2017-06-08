package com.starich.codewars.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jason on 2017/6/1.
 */
public class ValidPhoneNumberTest {

    public static boolean validPhoneNumber(String phoneNumber) {
        String regEx = "\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher mather = pattern.matcher(phoneNumber);
        return mather.matches();
    }

    public static void main(String[] args){
        System.out.println(validPhoneNumber("(123) 456-7890"));
        System.out.println(validPhoneNumber("(1111)555 2345"));
        System.out.println(validPhoneNumber("(098) 123 4567"));
    }
}
