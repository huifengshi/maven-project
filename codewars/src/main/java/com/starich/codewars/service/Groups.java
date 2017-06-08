package com.starich.codewars.service;

import java.util.Stack;

/**
 * Created by Jason on 2017/5/23.
 */
public class Groups {

    public static boolean groupCheck(String s){
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if("{".equals(String.valueOf(s.charAt(i)))
                || "(".equals(String.valueOf(s.charAt(i)))
                || "[".equals(String.valueOf(s.charAt(i)))
                    ){
                stack.push(String.valueOf(s.charAt(i)));
            }else{
                if("}".equals(String.valueOf(s.charAt(i)))){
                    if(!stack.empty() && "{".equals(stack.peek())){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
                if(")".equals(String.valueOf(s.charAt(i)))){
                    if(!stack.empty() && "(".equals(stack.peek())){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
                if("]".equals(String.valueOf(s.charAt(i)))){
                    if(!stack.empty() && "[".equals(stack.peek())){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
            }
        }
        return stack.empty();
    }

}
