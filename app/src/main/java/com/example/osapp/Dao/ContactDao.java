package com.example.osapp.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.osapp.models.ContactRemote;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contactremote")
    List<ContactRemote> index();

    @Query("SELECT * FROM contactremote WHERE user=:user")
    List<ContactRemote> get(String user);

    @Insert
    void insert(ContactRemote... contactRemotes);

    @Delete
    void delete(ContactRemote... contactRemotes);

    @Update
    void update(ContactRemote... contactRemotes);
}
