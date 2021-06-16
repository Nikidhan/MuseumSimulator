/**
 * TicketCounter.java simulate the environment of the ticket counter in the 
 * museum
 */
package com.company;

import javafx.application.Platform;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author Aiman, Zikri, Ahlami, Nik
 */
public class TicketCounter extends Thread {
    private int ticket_remaining;// Available ticket left
    Museum museum;
    Timer timer;
    Turnstile turnstile;
    int counter = 1; //ticket id counter
    int ticketSold = 0;
    Controller controller;

    /**
     * A TicketCounter constructor that is called in the Main to create
     * TicketCounter object. The initial available ticket will be initialize
     * when calling this constructor.
     * @param timer
     * @param museum
     * @param turnstile
     * @param num
     */
    public TicketCounter(Timer timer, Museum museum, Turnstile turnstile, Controller controller, int num){
        this.controller = controller;
        this.timer = timer;
        this.museum = museum;
        this.turnstile = turnstile;
        ticket_remaining=num;
        controller.setTicketSellingLogStatement("Ticket selling started.");
    }

    //Simulate cashier preparing the ticket

    //1) generate random number of ticket bought
    //2) generate ticket ID for tickets bought
    //3) called sale() and print "0000 Ticket 0001,0002 sold"
    //4) start all tickets thread using executor

    /**
     * A method prepare the ticket ID and also randomize the duration of one
     * visitor inside the museum.
     * @param current
     * @return ticketID
     */
    public String prepare(int current){
        String ticketID="";
        try{
            //generate ticketID
            String initial = "T";
            long number = current; //change to total left after this
            ticketID = initial + String.format("%04d",number);
            //randomize the duration of visitor inside the museum.
            Random rand= new Random();
            int duration = rand.nextInt(500);
            Thread.sleep(duration);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return ticketID;
    }

    /**
     * Method that process the decremental number of ticket remaining everytime
     * a visitor buy a ticket.
     */
    public void sale(){
        int tickets = ticket_remaining;
        if (tickets>0){
            tickets--;
            ticket_remaining = tickets;
            controller.setTicketRemainingTxtTxt(ticket_remaining);
            // System.out.println("Remaining ticket: "+ticket_remaining);
        }
    }

    @Override
    public void run(){

        //Simulate people randomly buying a random number of tickets within
        //time interval
        int ticket_bought=0;

        while(ticket_remaining>0 && timer.getCurrentHour()<17 && ticketSold <= museum.getMaxTotalCapacity()) {// Ticket selling time until 1700

            if (timer.getCurrentHour() == 17 && timer.getCurrentMin() >= 0) {
                break;
            }


            if (ticketSold == museum.getMaxTotalCapacity()) {
                controller.setTicketSellingLogStatement("The number of visitors has reached the daily visitor limit.");
                System.out.println("The number of visitors has reached the daily visitor limit.");
                break;
            }

            if(museum.getMaxTotalCapacity()-ticketSold<4 && museum.getMaxTotalCapacity()-ticketSold>0){
                ticket_bought = getRandomInteger(museum.getMaxTotalCapacity()-ticketSold,1);
            }else if(ticket_remaining<4 && ticket_remaining>0){
                ticket_bought = getRandomInteger(ticket_remaining,1);
            }else{
                ticket_bought = getRandomInteger(5,1); //max 4 ticket per customer
            }

            // Run the prepare method and sale method.
            int current = counter;
            String[] tickets = new String[ticket_bought];
            for(int j = 0; j<ticket_bought; j++){
                String ticketID = prepare(current);
                tickets[j] = ticketID;
                sale();
                current++;
            }

            //print tickets sold
            String ticketSoldLog = "Ticket(s)";
            //System.out.print(timer.current_time+" Tickets");
            for(int i = 0; i<ticket_bought;i++){
                if(i == ticket_bought-1){
                    ticketSoldLog += " " + tickets[i] + " sold";
                    //System.out.println(" " + tickets[i] + " sold");
                }else{
                    ticketSoldLog += " " + tickets[i] + ",";
                    //System.out.print(" " + tickets[i] + ",");
                }
//                Platform.runLater(()->{
//                    controller.setTicketSellingLogStatement(ticketSoldLog);
//                });
            }
                controller.setTicketSellingLogStatement(ticketSoldLog);


            // Execute Ticket class as a pool of Thread based on the ticket bought.
            ExecutorService executor = Executors.newFixedThreadPool(ticket_bought+1);
            for(int i = 0; i<ticket_bought; i++){
                Ticket ticket = new Ticket(timer,museum,turnstile,controller,counter);
                counter++;
                ticketSold++;
                executor.execute(ticket);
            }
            executor.shutdown();
        }

    }

    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}
