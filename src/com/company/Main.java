/**
 * The Main.java used to call other class that needed
 * and run the system. 
 */
package com.company;

import java.awt.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Aiman, Zikri, Ahlami, Nik
 */
public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        stage.setTitle("Museum Simulator");

        stage.setScene(new Scene(root,1000,750));
        stage.show();
    }

    public static void main(String[] args) {

//        Scanner read = new Scanner(System.in);
//
//        System.out.println("Enter the number of maximum current capacity:");
//        int max_current_capacity = read.nextInt();
//        System.out.println("Enter the number of maximum total capacity:");
//        int max_total_capacity = read.nextInt();
//        System.out.println("Enter the number of tickets:");
//        int totalTicket = read.nextInt();
//
//        Museum museum = new Museum(max_current_capacity,max_total_capacity);
//        Timer timer = new Timer();
//        Turnstile turn = new Turnstile(timer);
//        TicketCounter t1 = new TicketCounter(timer,museum,turn,totalTicket);
//
//        timer.start();
//        t1.start();
        launch(args);
    }





}
