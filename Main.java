package com.company;

public class Main {

    public static void main(String[] args) {
        Museum museum = new Museum(10,100);
        Timer timer = new Timer();
        Turnstile turn = new Turnstile(timer);
        TicketCounter t1 = new TicketCounter(timer,museum,turn,5);

        timer.start();
        t1.start();

    }
}
