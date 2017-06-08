package com.starich.component.test;

import com.starich.component.interf.GreetCustomer;
import com.starich.component.interf.SayHello;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jason on 2017/3/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-component-test.xml"})
public class SayHelloTest {

    @Spy
    @Autowired
    private SayHello sayHello;

    @InjectMocks
    @Autowired
    private GreetCustomer greetCustomer;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(sayHello.say("jason")).thenReturn("hello jason");
        //Mockito.when(sayHello.say("chris")).thenCallRealMethod();
    }

    @Test
    public void yellTest(){
        Assert.assertEquals("no,jason", sayHello.yell("jason"));
    }

    @Test
    public void sayTest(){
        Assert.assertEquals("hello jason", greetCustomer.welcome("jason"));

/*
        Assert.assertEquals("Hello World, this is chris", greetCustomer.welcome("chris"));
*/

    }


}
