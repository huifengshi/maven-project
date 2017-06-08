package com.starich.tomcat.servlet;

import java.io.IOException;

/**
 * Created by Jason on 2017/5/9.
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response){
        try{
            response.sendStaticResource();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
