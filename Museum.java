package com.company;

public class Museum {

    private int current_capacity;
    private int total_capacity;
    private int max_current_capacity;
    private int max_total_capacity;

    public Museum(){
        this.max_current_capacity = 100; //current capacity in museum at certain time
        this.max_total_capacity = 900; //ticket limit for a day
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

    public void print(){
        System.out.println("Current: "+ current_capacity + " out of " + max_current_capacity);
        System.out.println("Total: "+ total_capacity + " out of " + max_total_capacity);
    }

}
