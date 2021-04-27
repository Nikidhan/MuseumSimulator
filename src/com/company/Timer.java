package com.company;

public class Timer extends Thread{

        String current_time;
        private int openTime;
        int current_hour;
        int current_mins;
        public Timer(){

        }

        public int getCurrentHour(){
            return current_hour;
        }

        public int getCurrentMin(){
            return current_mins;
        }

        @Override
        public void run(){
            int num = 8;
            for(int hours=num; hours <= 18; hours++){
                for(int min=0; min <=59; min++){
                    String time = String.format("%02d", hours)+String.format("%02d", min);
                    try {
                        current_time = time;
                        current_hour = hours;
                        current_mins = min;
                        if(hours==9&&min==0){
                            System.out.println("Museum is opened");
                        }else if(hours==17&&min==50){
                            System.out.println("Museum is closing");
                        } else if(hours==18&&min==0){
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
