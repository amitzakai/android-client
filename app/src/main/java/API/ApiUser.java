package API;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.osapp.Chats;
import com.example.osapp.R;
import com.example.osapp.models.User;
import com.example.osapp.services.ContactService;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class ApiUser {
    private Retrofit r;
    private Api api;

    public ApiUser() {
        r = new Retrofit.Builder().baseUrl("http://10.0.2.2:7249/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = r.create(Api.class);
    }

    public void add_user(String id, String nickName, String password, AppCompatActivity a) {
        Call<Void> call2 = api.createUser(new User(id, password, nickName, new ContactService()
                , "7249"));
        call2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Intent i = new Intent(a, Chats.class);
                i.putExtra("userName", id);
                i.putExtra("nickName", nickName);
                a.startActivity(i);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void register(String id, String nickName, String password, TextView tv1, AppCompatActivity a) {
        Call<User> call = api.getUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    tv1.setText("username already exists");
                }
                else {
                    tv1.setText("");
                    add_user(id, nickName, password, a);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void sign_in(String userName, String password, TextView tv1, AppCompatActivity a) {
        Call<User> call = api.getUser(userName);
        call.enqueue(new Callback<User>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200) {
                    if(password.equals(response.body().getPassword())) {
                        Intent i = new Intent(a, Chats.class);
                        i.putExtra("userName", response.body().getId());
                        i.putExtra("nickName", response.body().getNickName());
                        a.startActivity(i);
                    } else {
                        tv1.setText("invalid password");
                    }
                }
                else {
                    tv1.setText("username does not exist");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getAll(TextView tv) {
        Call<List<User>> call = api.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
