package com.company;


import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Turnstile {

    private final Lock lock;
    private final Condition fullCondition;
    Timer timer;

    public Turnstile(Timer timer) {
        this.timer = timer;
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
    }

    public String getEntranceGate() {
        String[] gateName = {"NET1", "NET2", "NET3", "NET4", "SET1", "SET2", "SET3", "SET4"};
        return (gateName[new Random().nextInt(gateName.length)]);
    }

    public static String getExitGate() {
        String[] gateName = {"EET1", "EET2", "EET3", "EET4", "WET1", "WET2", "WET3", "WET4"};
        return (gateName[new Random().nextInt(gateName.length)]);
    }

    public void enter(String ticketID, long duration,Museum museum) throws InterruptedException{
        try{
            lock.lock();
            while(museum.getCurrentCapacity()==museum.getMaxCurrentCapacity()){
//                System.out.println("Masuk "+timer.current_time);
//                if(timer.current_time.charAt(1)=='9'){
//                    fullCondition.signalAll();
//                    System.out.println("Masuk");
//                }
                fullCondition.await();
            }
            System.out.println(timer.current_time+" Ticket "+ticketID+" entered through "+getEntranceGate()+". Staying for "+duration+" minutes.");
        }finally{
            lock.unlock();
        }

    }

    public void exit(String ticketID,Museum museum) throws InterruptedException{
        try{
            lock.lock();
            System.out.println(timer.current_time+ " Ticket "+ticketID+" exited through "+getExitGate());
            fullCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
