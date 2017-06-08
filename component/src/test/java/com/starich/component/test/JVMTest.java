package com.starich.component.test;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2017/3/15.
 */
public class JVMTest {

    @Test
    public void testHeapOOM(){
        List l = new ArrayList();
        while(true){
            l.add(new Object());
        }
    }

    /**
     * 测试SoftReference
     */
    @Test
    public void testSoftReference(){
        List l = new ArrayList();
        String s = "1234567890abcedfghijklmnopzrstuvwxyz!@#$%^&*()_+|~`";
        while (true) {
            SoftReference<Object[]> softrefObj = new SoftReference<Object[]>(new Object[10000000]);
            l.add(softrefObj);
            try {
                int j = 0;
                for(int k = 0; k < l.size(); k++){
                    if(((SoftReference<Object[]>)l.get(k)).get() != null){
                        j++;
                    }
                }
                System.out.println("size:" + l.size() + ",not null size:" + j);
                Thread.sleep(1000);
            }catch (InterruptedException ie){

            }
        }

    }


    /**
     * 测试WeakReference
     */
    @Test
    public void testWeakReference(){
        List l = new ArrayList();
        while (true) {
            WeakReference<Object[]> softrefObj = new WeakReference<Object[]>(new Object[10000000]);
            l.add(softrefObj);
            try {
                int j = 0;
                for(int k = 0; k < l.size(); k++){
                    if(((WeakReference<Object[]>)l.get(k)).get() != null){
                        j++;
                    }
                }
                System.out.println("size:" + l.size() + ",not null size:" + j);
                Thread.sleep(1000);
            }catch (InterruptedException ie){

            }
        }

    }


    //
    @Test
    public void testStackOverflowError(){
        int i = 0;
        int j = 1;
        System.out.println(i);
        System.out.println(j);
        testStackOverflowError();
    }
}
