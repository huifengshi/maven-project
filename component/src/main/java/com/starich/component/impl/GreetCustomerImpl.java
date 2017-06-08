package com.starich.component.impl;

import com.starich.component.interf.GreetCustomer;
import com.starich.component.interf.SayHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jason on 2017/3/11.
 */
@Service
public class GreetCustomerImpl implements GreetCustomer {

    @Autowired
    private SayHello sayHello;

    public String welcome(String name) {
        return sayHello.say(name);
    }
}
