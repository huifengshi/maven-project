package com.starich.core.jdk.thread;

public class ThreadDaemon {

    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println(System.currentTimeMillis() + ": sleep finish");
                } catch (Exception ex) {

                }
            }
        });
        thread.setDaemon(false);
        System.out.println(System.currentTimeMillis() + ": before start");
        thread.start();
        System.out.println(System.currentTimeMillis() + ": after start");

    }
}
