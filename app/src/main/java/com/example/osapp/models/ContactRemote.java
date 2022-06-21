package com.example.osapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.osapp.services.MessageService;

@Entity(primaryKeys = {"user", "id"})
public class ContactRemote {
    @NonNull
    private String user;
    @NonNull
    private String id;
    private String name;
    private String server;
    private String last;
    private String lastdate;

    public String getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getLast() {
        return last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public ContactRemote(String id, String name, String server, String last, String lastdate) {
        //this.user = user;
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
    }
}
