package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // thread utk handle UI changes, main --set controller , instance controller pass to timer thread, settext.
    @FXML
    public Text timerTxt;
    public Text ticketRemainingTxt;
    public TextField setDailyVisitorLimit, setCapacityLimit, setTicketLimit;
    public Circle openCircle;
    public Circle closingCircle;
    public Circle closedCircle;
    public TextArea netGate;
    public TextArea setGate;
    public TextArea eetGate;
    public TextArea wetGate;
    public TextArea ticketsSellingLog;
    public ListView museum;
    public ListView waitingList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldInteger();
    }

    public void setTimerTxt(String time){
        timerTxt.setText(time);
    }

    public void changeMuseumStatus (String status){
        if (status.equals("Open")) {
            closedCircle.setFill(Paint.valueOf("#ffffff"));
            openCircle.setFill(Paint.valueOf("#2ecc71"));
        } else if (status.equals("Closing")) {
            openCircle.setFill(Paint.valueOf("#ffffff"));
            closingCircle.setFill(Paint.valueOf("#f1c40f"));
        } else {
            closingCircle.setFill(Paint.valueOf("#ffffff"));
            closedCircle.setFill(Paint.valueOf("#e74c3c"));
        }
    }

    public void setTicketSellingLogStatement(String logStatement){
        ticketsSellingLog.appendText("\n"+logStatement);
    }

    public void enterNETGate(String visitor){
        netGate.appendText("\n"+visitor + " enter.");
        museum.getItems().add(visitor);
    }

    public void enterSETGate(String visitor){
        setGate.appendText("\n"+visitor + " enter.");
        museum.getItems().add(visitor);
    }

    public void exitEETGate(String visitor){
        eetGate.appendText("\n"+visitor + " exit.");
        museum.getItems().remove(visitor);
    }

    public void exitWETGate(String visitor){
        wetGate.appendText("\n"+visitor + " exit.");
        museum.getItems().remove(visitor);
    }

    public void waiting(String visitor){
        waitingList.getItems().add(visitor);
    }

    public void stopWaiting(String visitor){
        waitingList.getItems().remove(visitor);
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
        Turnstile turn = new Turnstile(timer,this);
        TicketCounter t1 = new TicketCounter(timer,museum,turn,this,ticketLimit);
        timer.start();
        t1.start();
    }
}
