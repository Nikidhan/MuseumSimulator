package com.company;
import java.util.*;

public class Ticket extends Thread{

    String ticketID;
    long timestamp;
    Museum museum;
    Turnstile turnstile;
    Timer timer;

    public Ticket (Timer timer, Museum museum, Turnstile turnstile, long timestamp){
        this.museum = museum;
        this.turnstile = turnstile;
        this.timestamp=timestamp;
        this.timer = timer;

        //generate ticketID
        String initial = "T";
        long number = timestamp; //change to total left after this
        ticketID = initial + String.format("%04d",number);

    }

    @Override
    public void run(){
        //simulate people enter the museum and view the gallery for a time being and exit via turnstile
        Random rand = new Random();
        int duration = rand.nextInt(150-50+1) + 50;
        turnstile.enter(timer.current_time,ticketID, duration);
        museum.visitor_enter();
        try {
            //tambah kalau museum penuh, org xleh masuk (later)

            Thread.sleep(duration * 200);
            turnstile.exit(timer.current_time,ticketID);
            museum.visitor_exit();

        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}
