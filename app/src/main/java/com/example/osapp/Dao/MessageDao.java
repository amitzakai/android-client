package com.example.osapp.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.osapp.models.ContactRemote;
import com.example.osapp.models.MessageRemote;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM messageremote")
    List<MessageRemote> index();

    @Query("SELECT * FROM messageremote WHERE user=:sender AND contact=:receiver")
    List<MessageRemote> get(String sender, String receiver);

    @Insert
    void insert(MessageRemote... messageRemotes);

    @Delete
    void delete(MessageRemote... messageRemotes);

    @Update
    void update(MessageRemote... messageRemotes);
}
