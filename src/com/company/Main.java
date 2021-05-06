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
        Scanner read = new Scanner(System.in);

        System.out.println("Enter the number of maximum current capacity:");
        int max_current_capacity = read.nextInt();
        System.out.println("Enter the number of maximum total capacity:");
        int max_total_capacity = read.nextInt();
        System.out.println("Enter the number of tickets:");
        int totalTicket = read.nextInt();

        Museum museum = new Museum(max_current_capacity,max_total_capacity);
        Timer timer = new Timer();
        Turnstile turn = new Turnstile(timer);
        TicketCounter t1 = new TicketCounter(timer,museum,turn,totalTicket);

        timer.start();
        t1.start();

    }
}
