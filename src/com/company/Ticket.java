/**
 * Ticket.java simulate visitor enter the museum, view the gallery for a time 
 * being and exit via turnstile 
 */
package com.company;
import java.util.*;
/**
 * @author Aiman, Zikri, Ahlami, Nik
 */
public class Ticket extends Thread{

    String ticketID;
    long timestamp;
    Museum museum;
    Turnstile turnstile;
    Timer timer;

    /**
     * A Ticket constructor that is called in the TicketCounter to create Ticket 
     * object.
     * @param timer
     * @param museum
     * @param turnstile
     * @param timestamp 
     */
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
        Random rand = new Random();
        int duration = rand.nextInt(150-50+1) + 50;// Duration visitor inside the museum are randomized
        int hours = duration/60;
        int minutes = duration%60;

        try {
            //No visitor enter at 0800
            while(timer.current_time.charAt(1)=='8'){
                
            }    
            turnstile.enter(ticketID,duration,museum);
            museum.visitor_enter();
            
            // To calculate the exit time 
            int exit_hour = timer.getCurrentHour() + hours;
            int exit_minute = timer.getCurrentMin() + minutes;

            // Calculate if the minute exceed 60 min = 1 hour
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
                // Visitor exit when the current time reach the exit time of the visitor
                if(exit_hour==timer.getCurrentHour()&&exit_minute==timer.getCurrentMin()){
                    turnstile.exit(ticketID, museum);
                    museum.visitor_exit();
                    break;
                    
                // Forcing all visitors inside the museum exit.    
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
