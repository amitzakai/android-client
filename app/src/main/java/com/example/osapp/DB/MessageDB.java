package com.example.osapp.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.osapp.Dao.ContactDao;
import com.example.osapp.Dao.MessageDao;
import com.example.osapp.models.MessageRemote;

@Database(entities = {MessageRemote.class}, version=1)
public abstract class MessageDB extends RoomDatabase {
    public abstract MessageDao messageDao();
}