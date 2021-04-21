package Museum;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Turnstile {

    private final Lock lock;
    private final Condition fullCondition;

    public Turnstile() {
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
    }

    public static String getEntranceGate() {
        try{
            lock.lock();
            while(list.isEmpty()){
                System.out.println("Stack is empty. Waiting..");
                emptyCondition.await(1, TimeUnit.SECONDS);
            }
            Integer o =  list.pop();
            fullCondition.signalAll();
            return o;
        } finally {
            lock.unlock();
        }
        String[] gateName = {"NET1", "NET2", "NET3", "NET4", "SET1", "SET2", "SET3", "SET4"};
        return (gateName[new Random().nextInt(gateName.length)]);
    }

    public static String getExitGate() {
        String[] gateName = {"EET1", "EET2", "EET3", "EET4", "WET1", "WET2", "WET3", "WET4"};
        return (gateName[new Random().nextInt(gateName.length)]);
    }

    public void enter(String current_time, String ticketID, long duration){
        System.out.println(current_time+" Ticket "+ticketID+" entered through "+getEntranceGate()+". Staying for "+duration+" minutes.");
    }

    public void exit(String current_time,String ticketID){
        System.out.println(current_time+ " Ticket "+ticketID+" exited through "+getExitGate());
    }
}
