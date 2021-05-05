/**
 * The Timer.java used to print the timestamp in 24 hours system. Timer will print
 * from 0800 which is ticket can be bought to 1800 which the museum close.
 * We assume that 200 ms = 1 minute for the museum environment.
 */
package com.company;
/**
 * @author Aiman, Zikri, Ahlami, Nik
 */
public class Timer extends Thread{

        String current_time;
        private int openTime;
        int current_hour;
        int current_mins;
                
        /**
         * Timer constructor that is called in the Main to create Timer object 
         * that run the time printing and also notify the Museum is opened, 
         * closing and closed.
         */
        public Timer(){

        }
        
        /**
         * Method that get only the current hour.
         * @return current_hour
         */
        public int getCurrentHour(){
            return current_hour;
        }
        
        /**
         * Method that get only the current minutes.
         * @return current_mins
         */
        public int getCurrentMin(){
            return current_mins;
        }

        /**
         * Method that run all the function of the Timer Thread.
         */
        @Override
        public void run(){
            int num = 8;// Initialize integer num with 8 because the ticket selling start at 0800.
                
            // Initialize integer hours that starting from 8 to 18 which represent 
            // the museum start selling ticket from 0800 and the museum closed at 1800.            
            for(int hours=num; hours <= 18; hours++){
                
                // Initialize integer min starting from 0 to 59 representing minutes in 
                // clock minutes start from 00 to 59.                    
                for(int min=0; min <=59; min++){
                     // Printing time in 24 hours system in string. e.g. 1750
                    String time = String.format("%02d", hours)+String.format("%02d", min);
                    try {
                        current_time = time;
                        current_hour = hours;
                        current_mins = min;
                        if(hours==9&&min==0){// The time museum open at 0900
                            System.out.println("Museum is opened");
                        }else if(hours==17&&min==50){// The time museum about to close at 1750
                            System.out.println("Museum is closing");
                        } else if(hours==18&&min==0){// The time museum close at 1800
                            System.out.println("Museum is closed");
                        }
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        }
}
