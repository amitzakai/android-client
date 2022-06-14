package com.example.osapp.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.osapp.models.Contact;

import java.util.List;

public class ContactsViewModel extends ViewModel {
    //private ContactRepository mRepository;
    private LiveData<List<Contact>> contacts;

    public ContactsViewModel(){
        //mRepository  = new ContactRepository();
        //contacts = mRepository.getAll();
    }

    public LiveData<List<Contact>> get() {return contacts;}

//    public void add(Contact contact){mRepository.add(contact);}
//
//    public void delete(Contact contact){mRepository.delete(contact);}
//
//    public void reload() {mRepository.reload();}


}
