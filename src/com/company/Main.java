/**
 * The Main.java used to call other class that needed
 * and run the system. 
 */
package com.company;
/**
 * @author Aiman, Zikri, Ahlami, Nik
 */
public class Main {

    public static void main(String[] args) {
        Museum museum = new Museum(100,900);
        Timer timer = new Timer();
        Turnstile turn = new Turnstile(timer);
        TicketCounter t1 = new TicketCounter(timer,museum,turn,900);

        timer.start();
        t1.start();

    }
}
