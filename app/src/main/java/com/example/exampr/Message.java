package com.example.exampr;

public class Message {
    private String sender;
    private String text;

    public Message() {
        // Firebase requires a default constructor
    }

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
// adapter
