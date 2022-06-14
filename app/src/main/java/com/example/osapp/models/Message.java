package com.example.osapp.models;

public class Message {
    private int id;
    private String content;
    private String created;
    private Boolean sent;

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
