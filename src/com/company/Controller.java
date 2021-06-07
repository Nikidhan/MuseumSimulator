package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // thread utk handle UI changes, main --set controller , instance controller pass to timer thread, settext.
    @FXML
    public Text timerTxt;
    public Text ticketRemainingTxt;
    public TextField setDailyVisitorLimit, setCapacityLimit, setTicketLimit;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldInteger();
    }

    public void setTimerTxt(String time){
        timerTxt.setText(time);
    }

    public void setTicketRemainingTxtTxt(int ticket){
        String ticketString = String.valueOf(ticket);
        ticketRemainingTxt.setText(ticketString);
    }

    public void textFieldInteger(){
        setDailyVisitorLimit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    setDailyVisitorLimit.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        setCapacityLimit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    setCapacityLimit.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });

        setTicketLimit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    setTicketLimit.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });
    }

    public void simulationStart(){
        int dailyVisitorLimit = Integer.parseInt(setDailyVisitorLimit.getText());
        int capacityLimit = Integer.parseInt(setCapacityLimit.getText());
        int ticketLimit = Integer.parseInt(setTicketLimit.getText());

        Timer timer = new Timer(this);
        Museum museum = new Museum(dailyVisitorLimit,capacityLimit);
        Turnstile turn = new Turnstile(timer);
        TicketCounter t1 = new TicketCounter(timer,museum,turn,this,ticketLimit);
        timer.start();
        t1.start();
    }
}
