package API;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osapp.Chats;
import com.example.osapp.Conversation;
import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.models.User;
import com.example.osapp.services.MessageService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiContact {

    private Retrofit r;
    private Api api;
    String request = "http://10.0.2.2:7249/api/";
    String server = "7249";
    public ApiContact() {
        r = new Retrofit.Builder().baseUrl(request)
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = r.create(Api.class);
    }

    public void getAll(String id, ContactsListAdapter adapter) {
        Call<List<Contact>> call = api.getAllContacts(id);
        call.enqueue(new Callback<List<Contact>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                adapter.setContacts(response.body());
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }


    public void addNewContact(String id, Contact c, ContactsListAdapter adapter, AppCompatActivity a) {
        Call<Void> call = api.addContact(id, c);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(adapter != null && a != null) {
                    getAll(id, adapter);
                    Intent i = new Intent(a, Conversation.class);
                    i.putExtra("userName", c.getId());
                    i.putExtra("nickName", c.getName());
                    a.startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    public void contactAddUser (String id, Contact c) {
        Call<User> call = api.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Contact user = new Contact(id, response.body().getNickName(), new MessageService()
                        , response.body().getServer(), null, null);
                addContact(c.getId(), user, null, null);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    public void addContact(String id, Contact c, ContactsListAdapter adapter, AppCompatActivity a) {
        Call<List<Contact>> call = api.getAllContacts(id);
        call.enqueue(new Callback<List<Contact>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if(c.findContact(response.body())) {

                } else {
                    if(server.equals(c.getServer())) {
                        contactAddUser(id, c);
                        addNewContact(id, c, adapter, a);
                    }
                    // invitation + add contact
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }
}
