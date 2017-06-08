package com.starich.worker.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Jason on 2017/5/13.
 */
public class QuartzTest {

    public static void main(String[] args){
        try{
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.shutdown();
        }catch (SchedulerException ex){
            ex.printStackTrace();
        }
    }
}
