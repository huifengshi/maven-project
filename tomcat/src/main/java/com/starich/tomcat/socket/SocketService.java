package com.starich.tomcat.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Jason on 2017/5/9.
 */
public class SocketService {
    private static final String IP = "172.20.17.53";
    private static final Integer PORT = 8080;

    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket(IP, PORT);
            OutputStream os = socket.getOutputStream();
            boolean autoFlush = true;
            PrintWriter out = new PrintWriter(os, autoFlush);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET /index.html HTTP/1.1");
            out.println("Host: " + IP + ":" + PORT);
            out.println("Connection: Close");
            out.println();
            boolean loop = true;
            StringBuffer sb = new StringBuffer(8096);
            while(loop){
                if(in.ready()){
                    int i = 0;
                    while(i != -1){
                        i = in.read();
                        sb.append((char)i);
                    }
                    loop = false;
                }
                Thread.sleep(50);
            }

            System.out.println(sb.toString());
        }catch (IOException ioe){

        }catch (InterruptedException iee){

        }finally {
            if(socket != null){
                try {
                    socket.close();
                }catch (IOException ioe){

                }
            }
        }
    }
}
