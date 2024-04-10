package com.models;

import java.time.LocalDate;

public class Message {
    private int id;
    private int senderMessageId;
    private String text;
    private LocalDate currentTimeStamp;

    //Costruttore
    public Message(int id, int senderMessageId, String text, LocalDate currentTimeStamp){
        this.id = id;
        this.senderMessageId = senderMessageId;
        this.text = text;
        this.currentTimeStamp = currentTimeStamp;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSenderMessage() {
        return senderMessageId;
    }
    public void setSenderMessage(int senderMessage) {
        this.senderMessageId = senderMessage; 
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public LocalDate getCurrentTimeStamp() {
        return currentTimeStamp;
    }
    public void setCurrentTimeStamp(LocalDate currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }
    
}


