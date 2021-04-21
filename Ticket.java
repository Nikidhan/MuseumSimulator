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
    public void run() {
        //simulate people enter the museum and view the gallery for a time being and exit via turnstile
        Random rand = new Random();
        int duration = rand.nextInt(150-50+1) + 50;
        int hours = duration/60;
        int minutes = duration%60;

        try {
            while(timer.current_time.charAt(1)=='8'){

            }

            turnstile.enter(ticketID,duration,museum);
            museum.visitor_enter();
            int exit_hour = timer.getCurrentHour() + hours;
            int exit_minute = timer.getCurrentMin() + minutes;
            System.out.println(ticketID+" expected exit time:" + String.format("%02d", exit_hour)+ String.format("%02d", exit_minute));

//            Thread.sleep(duration * 200);

            while(true){
                Thread.sleep(200);
//                System.out.println("Masa sekarang " + timer.current_time);
                if(exit_hour==timer.getCurrentHour()&&exit_minute==timer.getCurrentMin()){
                    break;
                }
            }

            turnstile.exit(ticketID, museum);
            museum.visitor_exit();

        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}
