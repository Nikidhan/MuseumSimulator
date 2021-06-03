package com.company;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
        // thread utk handle UI changes, main --set controller , instance controller pass to timer thread, settext.
    @FXML
    public Text timerTxt;
    public Text ticketRemainingTxt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setTimerTxt(String time){
        timerTxt.setText(time);
    }

    public void setTicketRemainingTxtTxt(int ticket){
        String ticketString = String.valueOf(ticket);
        ticketRemainingTxt.setText(ticketString);
    }

    public void simulationStart(){
        Timer timer = new Timer(this);
        Museum museum = new Museum(20,100);
        Turnstile turn = new Turnstile(timer);
        TicketCounter t1 = new TicketCounter(timer,museum,turn,this,100);
        timer.start();
        t1.start();
    }



}
