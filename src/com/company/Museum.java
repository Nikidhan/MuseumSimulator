package com.company;

public class Museum {

    private int current_capacity;
    private int total_capacity;
    private int max_current_capacity;
    private int max_total_capacity;

    public Museum(int max_current_capacity, int max_total_capacity ){
        this.max_current_capacity = max_current_capacity; //current capacity in museum at certain time
        this.max_total_capacity = max_total_capacity; //ticket limit for a day
    }

    public void visitor_enter(){
        current_capacity++;
        total_capacity++;
        print();
    }

    public void visitor_exit(){
        current_capacity--;
        print();
    }

    public int getCurrentCapacity(){
        return current_capacity;
    }

    public int getMaxCurrentCapacity(){
        return max_current_capacity;
    }

    public int getTotalCapacity(){
        return total_capacity;
    }

    public int getMaxTotalCapacity(){
        return max_total_capacity;
    }

    public void print(){
        System.out.println("Current: "+ current_capacity + " out of " + max_current_capacity);
        System.out.println("Total: "+ total_capacity + " out of " + max_total_capacity);
    }

}
