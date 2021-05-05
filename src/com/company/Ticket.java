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
            while(timer.curremt_time.charAt(1)=='8'){
                
            }    
            turnstile.enter(ticketID,duration,museum);
            museum.visitor_enter();
            int exit_hour = timer.getCurrentHour() + hours;
            int exit_minute = timer.getCurrentMin() + minutes;

            if(exit_minute>59){
                int add_hour=0;
                int new_min=0;
                add_hour = exit_minute/60;
                new_min = exit_minute%60;
                exit_hour = exit_hour + add_hour;
                exit_minute = new_min;
            }

            while(true){
                Thread.sleep(200);
                if(exit_hour==timer.getCurrentHour()&&exit_minute==timer.getCurrentMin()){
                    turnstile.exit(ticketID, museum);
                    museum.visitor_exit();
                    break;
                } else if(timer.getCurrentHour()==17&&timer.getCurrentMin()>55){
                    System.out.println(ticketID + "  is exiting because museum is closing");
                    turnstile.exit(ticketID, museum);
                    museum.visitor_exit();
                    break;
                }
            }

        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}
