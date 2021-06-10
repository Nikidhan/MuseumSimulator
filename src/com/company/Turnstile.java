/**
 * The Turnstile.java create an object that act as an environment 
 * have 2 entrance and 2 exit, each of them have 4 turnstiles.
 */
package com.company;
/**
 * @author Aiman, Zikri, Ahlami, Nik
 */
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Turnstile {

    private final Lock lock;
    private final Condition fullCondition;
    Timer timer;
    Controller controller;

    /**
     * A Turnstile constructor that is called in the Main to create Turnstile 
     * object that used the Timer class to print out the time when visitors 
     * enter and exit 
     * @param timer 
     */
    public Turnstile(Timer timer, Controller controller) {
        this.timer = timer;
        this.controller = controller;
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
    }

    /**
     * Method thar get the visitor's entrance gate and turnstile that randomly 
     * assign to every customer.
     * @return getName
     */
    public String getEntranceGate() {
        String[] gateName = {"NET1", "NET2", "NET3", "NET4", "SET1", "SET2", "SET3", "SET4"};
        return (gateName[new Random().nextInt(gateName.length)]);
    }

    /**
     * Method thar get the visitor's exit gate and turnstile that randomly 
     * assign to every customer.
     * @return getName
     */
    public static String getExitGate() {
        String[] gateName = {"EET1", "EET2", "EET3", "EET4", "WET1", "WET2", "WET3", "WET4"};
        return (gateName[new Random().nextInt(gateName.length)]);
    }

    /**
     * Method that prevent visitor to enter any turnstiles when the Museum current
     * capacity reached the maximum current capacity. Once the current capacity 
     * is less than the maximum current capacity, the visitor allow to enter and print 
     * the current time of entrance, ticket ID, entrance gate and the duration.
     * @param ticketID
     * @param duration
     * @param museum
     * @throws InterruptedException 
     */
    public void enter(String ticketID, long duration,Museum museum) throws InterruptedException{
        //Once visitor enter, it will be lock to check whether the capacity exceed 
        //the limit. While the capcity exceeded the limit, the system will await 
        //for signalAll which indicate there visitor exit to allow the visitor to 
        //enter.
        try{
            lock.lock();
            if(museum.getCurrentCapacity()==museum.getMaxCurrentCapacity()){
                //controller.setVisitorsLogStatement("Museum is currently full. Ticket "+ticketID+" is waiting.");
                //System.out.println("Museum is currently full. Ticket "+ticketID+" is waiting.");
                //controller.waiting(ticketID);
                while(museum.getCurrentCapacity()==museum.getMaxCurrentCapacity()){
                    fullCondition.await();//Wait for signalAll once visitor exit and new visitor to enter.
                }
                //controller.stopWaiting(ticketID);
            }

            String entranceGate = getEntranceGate();
            if(entranceGate.charAt(0)=='N'){
                controller.enterNETGate("Ticket "+ticketID);
            } else {
                controller.enterSETGate("Ticket "+ticketID);
            }
            //System.out.println(timer.current_time+" Ticket "+ticketID+" entered through "+getEntranceGate()+". Staying for "+duration+" minutes.");
        }finally{
            lock.unlock();
        }

    }

    /**
     * Method that keep track of visitor that exit, print the current time of exit, 
     * ticket ID and the exit gate. Then, it will signalAll to the enter method to
     * allow visitor to enter.
     * @param ticketID
     * @param museum
     * @throws InterruptedException 
     */
    public void exit(String ticketID,Museum museum) throws InterruptedException{
        try{
            lock.lock();
            String exitGate = getExitGate();
            if(exitGate.charAt(0)=='E'){
                controller.exitEETGate("Ticket "+ticketID);
            } else {
                controller.exitWETGate("Ticket "+ticketID);
            }
            //System.out.println(timer.current_time+ " Ticket "+ticketID+" exited through "+getExitGate());
            fullCondition.signalAll();//signal .await to allow next visitor to enter.
            controller.setCurrentTxt(museum.getCurrentCapacity());
        } finally {
            lock.unlock();
        }
    }
}
