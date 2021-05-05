/**
 * The Museum.java used to create an environment of a museum with 
 * visitors.
 */
package com.company;
/**
 * @author Aiman, Zikri, Ahlami, Nik
 */
public class Museum {

    private int current_capacity;//Parameter that collect the current capacity of the visitor.
    private int total_capacity;//Parameter that collect the total capacity of the visitor.
    private int max_current_capacity;//Parameter that limit the current capacity of the visitor.
    private int max_total_capacity;//Parameter that limit the total capacity of the visitor.
        
    /**
     * A Museum constructor that is called in the Main to create Museum object 
     * that set the maximum capacity at one time and set the max capacity for 
     * the whole iteration.
     * @param max_current_capacity
     * @param max_total_capacity 
     */
    public Museum(int max_current_capacity, int max_total_capacity ){
        this.max_current_capacity = max_current_capacity;
        this.max_total_capacity = max_total_capacity;
    }

    /**
     * Method that collect the current capacity and total capacity of the 
     * visitors.
     */
    public void visitor_enter(){
        current_capacity++;
        total_capacity++;
       // print();
    }

    /**
     * Method that decrease the number of current capacity of the visitor once
     * the visitor exit.
     */
    public void visitor_exit(){
        current_capacity--;
       // print();
    }

    /**
     * Method that get the current capacity of visitor in the Museum
     * @return current_capacity
     */
    public int getCurrentCapacity(){
        return current_capacity;
    }
    
    /**
     * Method that get the limit of the maximum current capacity of visitors 
     * at one time.
     * @return max_current_capacity
     */
    public int getMaxCurrentCapacity(){
        return max_current_capacity;
    }
    
    /**
     * Method that get the total capacity of visitors at that time.
     * @return total_capacity
     */
    public int getTotalCapacity(){
        return total_capacity;
    }
    
    /**
     * Method that get the limit of maximum total capacity of visitors.
     * @return max_total_capacity
     */
    public int getMaxTotalCapacity(){
        return max_total_capacity;
    }
    
    /**
     * Method that print the current and total capacity of visitors out of the 
     * maximum current capacity and total capacity.
     */
//    public void print(){
//        System.out.println("Current: "+ current_capacity + " out of " + max_current_capacity);
//        System.out.println("Total: "+ total_capacity + " out of " + max_total_capacity);
//    }

}
