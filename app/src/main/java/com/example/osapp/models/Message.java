package com.example.osapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String created;
    private Boolean sent;


    public Message(int id, String content, String created, Boolean sent) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.sent = sent;
    }

    //id:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //content:
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    //created:
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }

    //sent:
    public Boolean getSent() {
        return sent;
    }
    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}
