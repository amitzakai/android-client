package com.example.osapp.models;

import com.example.osapp.services.MessageService;

public class Contact {
    private String id;
    private String name;
    private MessageService messages;
    private String server;
    private String last;
    private String lastdate;

    //id:
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //name:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //messages:
    public MessageService getMessages() {
        return messages;
    }
    public void setMessages(MessageService messages) {
        this.messages = messages;
    }

    //server:
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }

    //last:
    public String getLast() {
        return last;
    }
    public void setLast(String last) {
        this.last = last;
    }

    //lastDate:
    public String getLastdate() {
        return lastdate;
    }
    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }
}
