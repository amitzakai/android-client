package API;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osapp.Chats;
import com.example.osapp.Conversation;
import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.models.Invitation;
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


    public void addNewContact(String id, Contact c, AppCompatActivity a) {
        Call<Void> call = api.addContact(id, c);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(a != null) {
                    Intent i = new Intent(a, Chats.class);
                    i.putExtra("userName", id);
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
                addNewContact(c.getId(), user, null);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void add_contact_in_my_server(String id, Contact c, AppCompatActivity a, TextView txt) {
        Call<User> call = api.getUser(c.getId());
        call.enqueue(new Callback<User>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    contactAddUser(id, c);
                    addNewContact(id, c, a);
                } else
                    txt.setText(c.getId() + " does not exist in your server, try another server!");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void add_contact_in_another_server(String id, Contact c, AppCompatActivity a
            , TextView txt) {
        Retrofit rOtherServer = new Retrofit.Builder().baseUrl(c.getServer())
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api apiOtherServer = rOtherServer.create(Api.class);
        Invitation i = new Invitation(id, c.getId(), "http://10.0.2.2:7249/");
        Call<Void> call = apiOtherServer.invitation(i);
        call.enqueue(new Callback<Void>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200)
                    addNewContact(id, c, a);
                else
                    txt.setText("sorry! we cannot find this contact in that server, make sure it" +
                            " is the correct userName!");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void addContact(String id, Contact c, AppCompatActivity a, TextView txt) {
        Call<List<Contact>> call = api.getAllContacts(id);
        call.enqueue(new Callback<List<Contact>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if(c.findContact(response.body())) {
                    txt.setText("you already have this contact!");
                } else {
                    if(server.equals(c.getServer())) {
                        txt.setText("");
                        add_contact_in_my_server(id, c, a, txt);
                    } else {
                        add_contact_in_another_server(id, c, a, txt);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }
}
