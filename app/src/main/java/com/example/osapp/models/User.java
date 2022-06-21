package com.example.osapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.osapp.services.ContactService;
public class User {
    private String id;
    private String password;
    private String nickName;
    private ContactService contacts;
    private String server;

    //id:
    public String getId() {
        return id;
    }
    public void setId(String id) {
        id = id;
    }

    //password:
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        password = password;
    }

    //nickName:
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        nickName = nickName;
    }

    //contacts:
    public ContactService getContacts() {
        return contacts;
    }
    public void setContacts(ContactService contacts) {
        this.contacts = contacts;
    }

    //server:
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }

    public User(String id, String password, String nickName, ContactService contacts, String server) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.contacts = contacts;
        this.server = server;
    }
}
