package com.company;

public class Main {

    public static void main(String[] args) {
        Museum museum = new Museum();
        Turnstile turn = new Turnstile();
        Timer timer = new Timer();
        TicketCounter t1 = new TicketCounter(timer,museum,turn,10);

        timer.start();
        t1.start();

    }
}
