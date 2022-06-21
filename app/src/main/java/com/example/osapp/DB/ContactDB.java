package com.example.osapp.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.osapp.Dao.ContactDao;
import com.example.osapp.models.ContactRemote;

@Database(entities = {ContactRemote.class}, version=1)
public abstract class ContactDB extends RoomDatabase {
    public abstract ContactDao contactDao();
}
