package com.company;

public class Timer extends Thread{

        String current_time;

        public Timer(){

        }

        @Override
        public void run(){
            int num = 8;
            for(int hours=num; hours <= 18; hours++){
                for(int min=1; min <=59; min++){
                    String time = String.format("%02d", hours)+String.format("%02d", min);
                    try{
                        current_time = time;
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        }
}
