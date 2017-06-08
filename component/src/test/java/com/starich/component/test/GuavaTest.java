package com.starich.component.test;

import com.google.common.base.Stopwatch;
import com.google.common.net.HostAndPort;
import com.google.common.net.InetAddresses;
import org.junit.Test;

import java.text.MessageFormat;

/**
 * Created by Jason on 2017/3/17.
 */
public class GuavaTest {

    @Test
    public void testStopWatch(){
        Stopwatch timer = Stopwatch.createStarted();
        try{
            Thread.sleep(100);
        }catch (InterruptedException ie){

        }
        System.out.println("Method took:" + timer.stop());
    }

    @Test
    public void testInetAddress(){
        String s = "127.0.0.1";
        boolean result = InetAddresses.isInetAddress(s);
        System.out.println(MessageFormat.format("is address:{0} valid?{1}",s, result));

        s = "127.0.0.1:9300";
        result = InetAddresses.isInetAddress(s);
        System.out.println(MessageFormat.format("is address:{0} valid?{1}",s, result));

        s = "localhost:9300";
        result = InetAddresses.isInetAddress(s);
        System.out.println(MessageFormat.format("is address:{0} valid?{1}",s, result));
    }

    @Test
    public void testHostAndPort(){
        HostAndPort hostAndPort = HostAndPort.fromString("127.0.0.1:9300");
        System.out.println(MessageFormat.format("host:{0}", hostAndPort.getHost()));
        System.out.println(MessageFormat.format("port:{0}", hostAndPort.getPort()));

    }
}
