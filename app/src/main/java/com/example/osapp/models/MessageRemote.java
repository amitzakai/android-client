package com.example.osapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id", "user","contact"})
public class MessageRemote {
    @NonNull
    private int id;
    @NonNull
    private String user;
    @NonNull
    private String contact;
    private String content;
    private String time;
    private boolean sent;

    public MessageRemote(int id, String user, String contact, String content, String time, Boolean sent) {
        this.id = id;
        this.user = user;
        this.contact = contact;
        this.content = content;
        this.time = time;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setTime(Boolean sent) {
        this.sent = sent;
    }
}
