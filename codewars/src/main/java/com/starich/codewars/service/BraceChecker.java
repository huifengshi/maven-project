package com.starich.codewars.service;

import java.util.Stack;

/**
 * Created by Jason on 2017/6/2.
 */
public class BraceChecker {
    public static boolean isValid(String braces) {
        Stack<String> bStack = new Stack<>();
        for(int i = 0; i < braces.length(); i++){
            switch (braces.charAt(i)){
                case '(':bStack.push("(");break;
                case '[':bStack.push("[");break;
                case '{':bStack.push("{");break;
                case ')':if(!bStack.empty() && "(".equals(bStack.peek())){
                    bStack.pop();
                }else{
                    return false;
                }break;
                case ']':if(!bStack.empty() && "[".equals(bStack.peek())){
                    bStack.pop();
                }else{
                    return false;
                }break;
                case '}':if(!bStack.empty() && "{".equals(bStack.peek())){
                    bStack.pop();
                }else{
                    return false;
                }break;
                default:return false;
            }
        }
        if(bStack.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        System.out.println(isValid("[(])"));
    }
}
