package com.company;


import java.util.Random;

public class Turnstile {


    public static String getRandom() {
        String[] gateName = {"NET1", "NET2", "NET3", "NET4", "SET1", "SET2", "SET3", "SET4", "EET1", "EET2", "EET3", "EET4", "WET1", "WET2", "WET3", "WET4",};
        return (gateName[new Random().nextInt(gateName.length)]);
    }

    public void enter(String current_time, String ticketID, long duration){
        System.out.println(current_time+" Ticket "+ticketID+" entered through "+getRandom()+". Staying for "+duration+" minutes.");
    }

    public void exit(String current_time,String ticketID){
        System.out.println(current_time+ " Ticket "+ticketID+" exited through "+getRandom());
    }
}
